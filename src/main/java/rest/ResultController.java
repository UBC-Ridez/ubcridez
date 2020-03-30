package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.ResultService;

@RestController
@RequestMapping(value =
{ "/result" })
@CrossOrigin
public class ResultController
{

  @Autowired
  ResultService resultService;
  
  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/columns", method = RequestMethod.GET)
  public List<String> getColumns(@PathVariable(value = "tableName") String tableName)
  {
    
    EntityManager em = entityManagerFactory.createEntityManager();
    try
    {
      return (List<String>) em
          .createNativeQuery("SELECT getColumns(:tableName);")
          .setParameter("tableName", tableName).getResultList();
    } finally
    {
      try
      {
        em.clear();
        em.close();
      } catch (Exception e)
      {
      }

    }
  }
    
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/getConditionedResults", method = RequestMethod.GET)
  @ResponseBody
  public List<Map<String,Object>> getConditionedResults(@PathVariable(value = "tableName") String tableName, @RequestParam(value = "cond", defaultValue = "", required = false) String cond) throws IOException
  {
    EntityManager em = entityManagerFactory.createEntityManager();
   
    String query = cond.equalsIgnoreCase("") ? String.format("SELECT * from %s;", tableName) : String.format("SELECT * from %s where %s;", tableName, cond);
    Session session =  em.unwrap(Session.class);

//    List<Object[]> list = em.createNativeQuery(query).getResultList();
    try
    {
      Query q = session.createSQLQuery(query);
      q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      List<Map<String, Object>> aliasToValueMapList = q.list();

      return aliasToValueMapList;
    } finally
    {
      if (session != null && session.isOpen())
      {
        try
        {
          session.clear();
          session.close();
          em.clear();
          em.close();
        } catch (Exception e)
        {
        }
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/getProjectedResults", method = RequestMethod.GET)
  @ResponseBody
  public List<LinkedHashMap<String,Object>> getProjectedResults(@PathVariable(value = "tableName") String tableName, @RequestParam(value = "columns", defaultValue = "id", required = false) String columns) throws IOException
  {
    EntityManager em = entityManagerFactory.createEntityManager();
   
    String query = String.format("SELECT %s from %s;", columns, tableName);
    Session session =  em.unwrap(Session.class);

    //    List<Object[]> list = em.createNativeQuery(query).getResultList();
    try
    {
      Query q = session.createSQLQuery(query);
      q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      List<Map<String, Object>> aliasToValueMapList = q.list();
      List<LinkedHashMap<String, Object>> orderedMapList = new ArrayList<>();
      for (Map<String, Object> linkedHashMap : aliasToValueMapList)
      {
        LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<String, Object>();
        for (String column : columns.split(","))
        {
          orderedMap.put(column, linkedHashMap.get(column));
        }
        orderedMapList.add(orderedMap);
      }
      return orderedMapList;
    } finally
    {
      if (session != null && session.isOpen())
      {
        try
        {
          session.clear();
          session.close();
          em.clear();
          em.close();
        } catch (Exception e)
        {
        }
      }
    }
  }
}
