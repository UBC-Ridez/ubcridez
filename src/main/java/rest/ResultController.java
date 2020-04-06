package rest;

import java.io.IOException;
import java.util.ArrayList;
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
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/getAggResults", method = RequestMethod.GET)
  @ResponseBody
  public List<LinkedHashMap<String,Object>> getAggResults(@PathVariable(value = "tableName") String tableName, @RequestParam(value = "aggFunc", defaultValue = "count", required = false) String aggFunc,
      @RequestParam(value = "aggColumn", defaultValue = "*", required = false) String aggColumn, @RequestParam(value = "columns", defaultValue = "", required = false) String columns) throws IOException
  {
    EntityManager em = entityManagerFactory.createEntityManager();
    aggFunc = aggFunc.toLowerCase();
    String query = columns.equalsIgnoreCase("")
        ? String.format("SELECT %s(%s) as %s from %s;", aggFunc, aggColumn, aggFunc, tableName)
        : String.format("SELECT %s(%s) as %s,%s from %s group by %s;", aggFunc,
            aggColumn, aggFunc, columns, tableName, columns);
    Session session = em.unwrap(Session.class);

    //    List<Object[]> list = em.createNativeQuery(query).getResultList();
    columns = columns.contentEquals("") ? aggFunc : aggFunc + "," + columns;
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
  
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/getJoinResults/{tableName2}", method = RequestMethod.GET)
  @ResponseBody
  public List<LinkedHashMap<String,Object>> getAggResults(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "tableName2") String tableName2,
      @RequestParam(value = "projectedColumns", defaultValue = "id", required = false) String projectedColumns,
      @RequestParam(value = "projectedColumns2", defaultValue = "id", required = false) String projectedColumns2,
      @RequestParam(value = "joinColumn", defaultValue = "id", required = false) String joinColumn,
      @RequestParam(value = "joinColumn2", defaultValue = "id", required = false) String joinColumn2) throws IOException
  {
    EntityManager em = entityManagerFactory.createEntityManager();
    List<String> projectedColumnsArr = new ArrayList<>();
    for (String column : projectedColumns.split(","))
    {
      projectedColumnsArr.add("a." + column  + " " + tableName + column.substring(0, 1).toUpperCase() + column.substring(1));
    }
    projectedColumns = String.join(",", projectedColumnsArr);
    
    projectedColumnsArr = new ArrayList<>();
    for (String column : projectedColumns2.split(","))
    {
      projectedColumnsArr.add("b." + column  + " " + tableName2 + column.substring(0, 1).toUpperCase() + column.substring(1));
    }
    projectedColumns2 = String.join(",", projectedColumnsArr);

    String query = 
        String.format("Select %s,%s from %s a join %s b ON a.%s = b.%s", projectedColumns, projectedColumns2, tableName, tableName2, joinColumn, joinColumn2);
    Session session = em.unwrap(Session.class);

    try
    {
      Query q = session.createSQLQuery(query);
      q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      List<Map<String, Object>> aliasToValueMapList = q.list();
      List<LinkedHashMap<String, Object>> orderedMapList = new ArrayList<>();
      for (Map<String, Object> linkedHashMap : aliasToValueMapList)
      {
        LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<String, Object>();
        for (String column : projectedColumns.split(","))
        {
          orderedMap.put(column.split(" ")[1], linkedHashMap.get(column.split(" ")[1].toLowerCase()));
        }
        for (String column : projectedColumns2.split(","))
        {
          orderedMap.put(column.split(" ")[1], linkedHashMap.get(column.split(" ")[1].toLowerCase()));
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
  

  
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/{tableName}/getDivisionResults/{tableName2}/{tableName3}", method = RequestMethod.GET)
  @ResponseBody
  public List<LinkedHashMap<String,Object>> getAggResults(@PathVariable(value = "tableName") String tableName, @PathVariable(value = "tableName2") String tableName2,
      @PathVariable(value = "tableName3") String tableName3,
      @RequestParam(value = "projectedColumns", defaultValue = "id", required = false) String projectedColumns,
      @RequestParam(value = "firstTableColumn", defaultValue = "id", required = false) String firstTableColumn,
      @RequestParam(value = "secondTableColumn", defaultValue = "id", required = false) String secondTableColumn,
      @RequestParam(value = "thirdTableColumn1", defaultValue = "id", required = false) String thirdTableColumn1,
      @RequestParam(value = "thirdTableColumn2", defaultValue = "id", required = false) String thirdTableColumn2) throws IOException
  {
    EntityManager em = entityManagerFactory.createEntityManager();
    List<String> projectedColumnsArr = new ArrayList<>();
    for (String column : projectedColumns.split(","))
    {
      projectedColumnsArr.add("s." + column  + " " + tableName + column.substring(0, 1).toUpperCase() + column.substring(1));
    }
    projectedColumns = String.join(",", projectedColumnsArr);
    
    String query = 
        String.format("  SELECT %s " + 
            "  FROM %s s " + 
            "  WHERE NOT EXISTS " + 
            "  (SELECT * from %s c " + 
            "  WHERE NOT EXISTS " + 
            "  (SELECT E.%s " + 
            "  FROM %s E " + 
            "  WHERE S.%s=E.%s AND " + 
            "  C.%s=E.%s));", projectedColumns, tableName, tableName2, thirdTableColumn1, tableName3, firstTableColumn, thirdTableColumn1, secondTableColumn, thirdTableColumn2);
    Session session = em.unwrap(Session.class);

    try
    {
      Query q = session.createSQLQuery(query);
      q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
      List<Map<String, Object>> aliasToValueMapList = q.list();
      List<LinkedHashMap<String, Object>> orderedMapList = new ArrayList<>();
      for (Map<String, Object> linkedHashMap : aliasToValueMapList)
      {
        LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<String, Object>();
        for (String column : projectedColumns.split(","))
        {
          orderedMap.put(column.split(" ")[1], linkedHashMap.get(column.split(" ")[1].toLowerCase()));
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
