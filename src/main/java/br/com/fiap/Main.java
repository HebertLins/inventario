package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();


        TipoDeBem tipo = new TipoDeBem();
        tipo.setId(null)
                .setNome("casinha");


        Departamento dep = new Departamento();
        dep.setId(null)
                .setNome("Departamento Abdec");

        Inventario inventario = new Inventario();
        inventario.setId(null)
                .setRelatorio("Imovel em bom estado")
                .setDepartamento(dep)
                .setInicio(LocalDate.of(1990,10,21));

        Bem bem = new Bem();
        bem.setId(null)
                .setNome("Casa 567")
                .setEtiqueta("TesteEtiqueta2")
                .setLocalizacao(dep)
                .setTipo(tipo)
                .setAquisicao(LocalDate.of(2000,03,10));

        manager.getTransaction().begin();
        manager.persist(bem);
        manager.getTransaction().commit();

        Long id = Long.valueOf(JOptionPane.showInputDialog("ID do Inventario:"));
        Inventario inventario1 = manager.find(Inventario.class, id);
        System.out.println(inventario1);
        //ID 52 PARA TESTES

        List<Bem> list = manager.createQuery("FROM Bem").getResultList();
        list.forEach(System.out::println);

        manager.close();
        factory.close();
    }


}