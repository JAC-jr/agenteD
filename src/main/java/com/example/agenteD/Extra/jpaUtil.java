package com.example.agenteD.Extra;

import java.sql.ResultSetMetaData;

public class jpaUtil {

    public class JPAUtil {

        /* public JPAUtil() throws Exception{
          *    Class.forName("org.hsqldb.jdbcDriver");
               System.out.println("Driver Loaded.");
               String url = "jdbc:hsqldb:data/tutorial";

               Connection conn = DriverManager.getConnection(url, "sa", "");
               System.out.println("Got Connection.");
               stmt = conn.createStatement();
           }

        public void executeSQLCommand(String sql) throws Exception {
            stmt.executeUpdate(sql);
        }
        public void checkData(String sql) throws Exception {
            rs = stmt.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();

            for (int i = 0; i < metadata.getColumnCount(); i++) {
                System.out.print("\t"+ metadata.getColumnLabel(i + 1));
            }
            System.out.println("\n----------------------------------");

            while (rs.next()) {
                for (int i = 0; i < metadata.getColumnCount(); i++) {
                    Object value = rs.getObject(i + 1);
                    if (value == null) {
                        System.out.print("\t       ");
                    } else {
                        System.out.print("\t"+value.toString().trim());
                    }
                }
                System.out.println("");
            }
        }
    }
    public class ProfessorService {
        protected EntityManager em;

        public ProfessorService(EntityManager em) {
            this.em = em;
        }

        public Professor createProfessor(int id, String name, long salary) {
            Professor emp = new Professor(id);
            emp.setName(name);
            emp.setSalary(salary);
            em.persist(emp);
            return emp;
        }

        public void removeProfessor(int id) {
            Professor emp = findProfessor(id);
            if (emp != null) {
                em.remove(emp);
            }
        }

        public Professor raiseProfessorSalary(int id, long raise) {
            Professor emp = em.find(Professor.class, id);
            if (emp != null) {
                emp.setSalary(emp.getSalary() + raise);
            }
            return emp;
        }

        public Professor findProfessor(int id) {
            return em.find(Professor.class, id);
        }

        public Collection<Professor> findAllProfessors() {
            Query query = em.createQuery("SELECT e FROM Professor e");
            return (Collection<Professor>) query.getResultList();
        }
    }
   // Main


    public class Main {
        public static void main(String[] a) throws Exception {
            JPAUtil util = new JPAUtil();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProfessorService");
            EntityManager em = emf.createEntityManager();
            ProfessorService service = new ProfessorService(em);

            em.getTransaction().begin();

            Professor emp = service.createProfessor(1,"name", 100);
            emp = service.createProfessor(2,"name 2", 100);

            Collection emps = em.createQuery("SELECT e FROM Professor e").getResultList();
            for (Iterator i = emps.iterator(); i.hasNext();) {
                Professor e = (Professor) i.next();
                System.out.println("Professor " + e.getId() + ", " + e.getName());
            }
            util.checkData("select * from Professor");

            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
    */

    }
}
