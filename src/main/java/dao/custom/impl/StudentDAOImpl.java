package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> studentArrayList = session.createNativeQuery("SELECT * FROM Student").addEntity(Student.class).list();

        transaction.commit();
        session.close();
        return studentArrayList;
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query =  session.createNamedQuery("Student.findLatestUserId", String.class);
        query.setMaxResults(1);
        String latestStudentId = query.uniqueResult();

        if (latestStudentId != null){
            transaction.commit();
            session.close();
            int newStudentID = Integer.parseInt(latestStudentId.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newStudentID);
        }else {
            return "S00-001";
        }
    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Student WHERE student_id =:studentId");
        nativeQuery.setParameter("studentId",id);
        nativeQuery.addEntity(Student.class);
        Student student = (Student) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return student;

    }


    @Override
    public List<Student> getUnpaidStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT DISTINCT s.student_id,s.name,s.address,s.contact_no,s.date,s.gender FROM student s JOIN reservation r on s.student_id = r.student_student_id WHERE r.status='un-paid'");
        nativeQuery.addEntity(Student.class);
        List<Student> customers = nativeQuery.list();


        transaction.commit();
        session.close();

        return customers;
    }
}
