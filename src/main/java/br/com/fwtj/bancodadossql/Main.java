/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.bancodadossql;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Junior
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Class> classes1 = new ArrayList<>();
        classes1.add(Pessoa.class);
        JPAUtil util1 = new JPAUtil();
        EntityManager entityManagerDinamica1 = util1.getEntityManagerDinamica(
                "PuDinamico1",
                "bd1",
                "jdbc:mysql://localhost/db1",
                "root",
                "fwtjsistemas",
                "com.mysql.jdbc.Driver",
                "org.hibernate.dialect.MySQL5Dialect",
                "true",
                "true",
                "update",
                classes1);
        EntityTransaction transaction1 = entityManagerDinamica1.getTransaction();
        transaction1.begin();

        ArrayList<Class> classes2 = new ArrayList<>();
        classes2.add(Endereco.class);
        JPAUtil util2 = new JPAUtil();
        EntityManager entityManagerDinamica2 = util2.getEntityManagerDinamica(
                "PuDinamico2",
                "db2",
                "jdbc:mysql://localhost/db2",
                "root",
                "fwtjsistemas",
                "com.mysql.jdbc.Driver",
                "org.hibernate.dialect.MySQL5Dialect",
                "true",
                "true",
                "update",
                classes2);
        EntityTransaction transaction2 = entityManagerDinamica2.getTransaction();
        transaction2.begin();

        List<Pessoa> resultList1 = entityManagerDinamica1.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        List<Endereco> resultList2 = entityManagerDinamica2.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
        
        for (Pessoa pessoa : resultList1) {
            System.out.println(pessoa.getId() + " - " + pessoa.getNome());
        }
        
        for (Endereco endereco : resultList2) {
            System.out.println(endereco.getId() + " - " + endereco.getRua() + ", " + endereco.getNumero());
        }
        
        entityManagerDinamica1.close();
        entityManagerDinamica2.close();

    }

}
