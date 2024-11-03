/*package com.esprit.examen.services;

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
import com.esprit.examen.repositories.StockRepository;

@RunWith(MockitoJUnitRunner.class)
@Slf4j  // Utilisation de Lombok pour le logger
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository; // Mock du dépôt

    @InjectMocks
    private StockServiceImpl stockServiceImpl; // Classe à tester

    @Before
    public void setUp() {
        // Configuration de Mockito si nécessaire
    }

    @Test
    public void testAddStock() {
        Stock s = new Stock("stock test", 10, 100);
        when(stockRepository.save(any(Stock.class))).thenAnswer(invocation -> {
            Stock stock = invocation.getArgument(0);
            stock.setIdStock(1L); // Simulate ID generation
            return stock;
        });

        Stock savedStock = stockServiceImpl.addStock(s);
        assertNotNull(savedStock);
        assertNotNull(savedStock.getIdStock()); // Ensure the ID is set
        log.info("Stock ajouté : {}", savedStock.getLibelleStock());

        stockServiceImpl.deleteStock(savedStock.getIdStock());
        log.info("Stock supprimé : {}", savedStock.getIdStock());
    }


    @Test
    public void testDeleteStock() {
        Stock s = new Stock("stock test", 30, 60);
        when(stockRepository.save(s)).thenReturn(s); // Simulation de la méthode save
        Stock savedStock = stockServiceImpl.addStock(s);

        stockServiceImpl.deleteStock(savedStock.getIdStock());
        assertNull(stockServiceImpl.retrieveStock(savedStock.getIdStock()));
        log.warn("Stock supprimé : {}", savedStock.getIdStock());
    }

    @Test
    public void testAddStockError() {
        Stock s = new Stock("stock test", 10, 100);

        // Simuler une exception lors de l'ajout d'un stock
        when(stockRepository.save(s)).thenThrow(new RuntimeException("Erreur d'ajout du stock"));

        try {
            stockServiceImpl.addStock(s);
            fail("Une exception aurait dû être levée"); // Si l'exception n'est pas levée, échouer le test
        } catch (RuntimeException e) {
            log.error("Erreur lors de l'ajout du stock : {}", e.getMessage());
            assertEquals("Erreur d'ajout du stock", e.getMessage());
        }
    }
}
*/