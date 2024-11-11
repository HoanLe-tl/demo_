package repository;

import entity.Users;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    public List<Users> getList() {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select u from Users u");
        List<Users> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }
    public Users getUser(String id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select u from Users u where u.id = : idUser");
        query.setParameter("idUser", id);
        Users user = (Users) query.getSingleResult();
        transaction.commit();
        session.close();
        return user;
    }
    public void add(Users user) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
    public void deleteUser(Users user) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
    public void update(Users users, String id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(id, users);
        transaction.commit();
        session.close();
    }
    public List<Users> getUserBySearch(String loai, String search) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Query query;
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("select u from Users u where :loai = :search");
        q.setParameter("loai", loai);
        q.setParameter("search", search);
        List<Users> list = q.getResultList();
        return (List<Users>) list;


//        if(loai.contains("ID")){
//            Query q = session.createQuery("select u from Users u where u.id = :loai");
//            q.setParameter("loai", search);
//            List<Users> list = q.getResultList();
//            return (List<Users>) list;
//        }
//        else{
//            Query q = session.createQuery("select u from Users u where u.fullName = :loai");
//            q.setParameter("loai", search);
//            List<Users> list = q.getResultList();
//            return (List<Users>) list;
//        }
    }

    public static void main(String[] args) {
        List<Users> list = new UserRepo().getList();
        for (Users u : list) {
            System.out.println(u.toString());
        }
    }



}
