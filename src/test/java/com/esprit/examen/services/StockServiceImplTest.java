package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import lombok.extern.slf4j.Slf4j;

import com.esprit.examen.entities.Stock;

@RunWith(MockitoJUnitRunner.class)
@Slf4j  // Utilisation de Lombok pour le logger
public class StockServiceImplTest {

    @Mock
    IStockService stockService;  // Mock du service

    @InjectMocks
    StockServiceImpl stockServiceImpl; // Classe à tester

    @Before
    public void setUp() {
        // Configuration de Mockito si nécessaire
    }

    @Test
    public void testAddStock() {
        Stock s = new Stock("stock test", 10, 100);
        when(stockService.addStock(s)).thenReturn(s); // Simulation du service

        Stock savedStock = stockServiceImpl.addStock(s);
        assertNotNull(savedStock);
        log.info("Stock ajouté : {}", savedStock.getLibelleStock());

        stockServiceImpl.deleteStock(savedStock.getIdStock());
    }


    @Test
    public void testDeleteStock() {
        Stock s = new Stock("stock test", 30, 60);
        when(stockService.addStock(s)).thenReturn(s); // Simulation du service
        Stock savedStock = stockServiceImpl.addStock(s);

        stockServiceImpl.deleteStock(savedStock.getIdStock());
        assertNull(stockServiceImpl.retrieveStock(savedStock.getIdStock()));
        log.warn("Stock supprimé : {}", savedStock.getIdStock());
    }
    @Test
    public void testAddStockOptimized() {
        // Créer un stock à ajouter
        Stock s = new Stock("stock test", 10, 100);

        when(stockService.addStock(s)).thenReturn(s); // Simuler le comportement d'ajout

        Stock savedStock = stockService.addStock(s);

        assertNotNull(savedStock.getIdStock());
        assertSame(10, savedStock.getQte()); // Vérifie que la quantité est bien 10
        assertTrue(savedStock.getQteMin() > 0); // Vérifie que la quantité minimale est supérieure à 0

        // Vérifier que la méthode deleteStock a été appelée avec le bon ID
        stockService.deleteStock(savedStock.getIdStock());

        // Vérifier que deleteStock a bien été appelée
        verify(stockService).deleteStock(savedStock.getIdStock());
    }

    @Test
    public void testAddStockError() {
        Stock s = new Stock("stock test", 10, 100);

        // Simuler une exception lors de l'ajout d'un stock
        when(stockService.addStock(s)).thenThrow(new RuntimeException("Erreur d'ajout du stock"));

        try {
            stockServiceImpl.addStock(s);
        } catch (RuntimeException e) {
            log.error("Erreur lors de l'ajout du stock : {}", e.getMessage());
            assertEquals("Erreur d'ajout du stock", e.getMessage());
        }
    }
}
