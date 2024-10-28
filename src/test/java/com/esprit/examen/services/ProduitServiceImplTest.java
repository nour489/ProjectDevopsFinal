package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.esprit.examen.entities.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduit() {
        Produit p = new Produit();
        p.setLibelleProduit("Produit Test");
        p.setPrix(100.0f);

        when(produitRepository.save(any(Produit.class))).thenReturn(p);

        Produit savedProduit = produitService.addProduit(p);

        assertNotNull(savedProduit);
        assertEquals("Produit Test", savedProduit.getLibelleProduit());
        verify(produitRepository, times(1)).save(p);
    }

    @Test
    public void testRetrieveAllProduits() {
        Produit p = new Produit();
        p.setLibelleProduit("Produit Test");
        p.setPrix(100.0f);

        List<Produit> produitList = new ArrayList<>();
        produitList.add(p);

        when(produitRepository.findAll()).thenReturn(produitList);

        List<Produit> produits = produitService.retrieveAllProduits();

        assertEquals(1, produits.size());
        assertEquals("Produit Test", produits.get(0).getLibelleProduit());
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteProduit() {
        Long produitId = 1L;

        produitService.deleteProduit(produitId);

        verify(produitRepository, times(1)).deleteById(produitId);
    }

    @Test
    public void testUpdateProduit() {
        Produit p = new Produit();
        p.setIdProduit(1L);
        p.setLibelleProduit("Produit Test");
        p.setPrix(100.0f);

        when(produitRepository.save(any(Produit.class))).thenReturn(p);

        Produit updatedProduit = produitService.updateProduit(p);

        assertNotNull(updatedProduit);
        assertEquals("Produit Test", updatedProduit.getLibelleProduit());
        verify(produitRepository, times(1)).save(p);
    }

    @Test
    public void testRetrieveProduit() {
        Long produitId = 1L;
        Produit p = new Produit();
        p.setIdProduit(produitId);
        p.setLibelleProduit("Produit Test");
        p.setPrix(100.0f);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(p));

        Produit retrievedProduit = produitService.retrieveProduit(produitId);

        assertNotNull(retrievedProduit);
        assertEquals("Produit Test", retrievedProduit.getLibelleProduit());
        verify(produitRepository, times(1)).findById(produitId);
    }

    @Test
    public void testAssignProduitToStock() {
        Long produitId = 1L;
        Long stockId = 1L;
        Produit p = new Produit();
        p.setIdProduit(produitId);
        Stock s = new Stock();
        s.setIdStock(stockId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(p));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(s));

        produitService.assignProduitToStock(produitId, stockId);

        assertEquals(s, p.getStock());
        verify(produitRepository, times(1)).save(p);
    }
}
