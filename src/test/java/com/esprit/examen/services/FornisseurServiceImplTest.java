package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

public class FornisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test pour retrieveAllFournisseurs()
    @Test
    public void testRetrieveAllFournisseurs() {
        Fournisseur fournisseur1 = new Fournisseur();
        fournisseur1.setIdFournisseur(1L);
        fournisseur1.setDetailFournisseur(new DetailFournisseur());

        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setIdFournisseur(2L);
        fournisseur2.setDetailFournisseur(new DetailFournisseur());

        when(fournisseurRepository.findAll()).thenReturn(Arrays.asList(fournisseur1, fournisseur2));

        List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();

        assertEquals(2, fournisseurs.size());
        verify(fournisseurRepository, times(1)).findAll();
    }

    // Test pour addFournisseur()
    @Test
    public void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setDetailFournisseur(new DetailFournisseur());

        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur savedFournisseur = fournisseurService.addFournisseur(fournisseur);

        assertNotNull(savedFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    // Test pour updateFournisseur()
    @Test
    public void testUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setDetailFournisseur(new DetailFournisseur());

        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(fournisseur);

        assertNotNull(updatedFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    // Test pour deleteFournisseur()
    @Test
    public void testDeleteFournisseur() {
        Long fournisseurId = 1L;
        fournisseurService.deleteFournisseur(fournisseurId);
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }

    // Test pour retrieveFournisseur()
    @Test
    public void testRetrieveFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setDetailFournisseur(new DetailFournisseur());

        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));

        Fournisseur foundFournisseur = fournisseurService.retrieveFournisseur(1L);

        assertNotNull(foundFournisseur);
        assertEquals(1L, foundFournisseur.getIdFournisseur());
        verify(fournisseurRepository, times(1)).findById(1L);
    }

    // Test pour assignSecteurActiviteToFournisseur()
    @Test
    public void testAssignSecteurActiviteToFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setSecteurActivites(new HashSet<>());

        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);

        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteurActivite));

        fournisseurService.assignSecteurActiviteToFournisseur(1L, 1L);

        assertTrue(fournisseur.getSecteurActivites().contains(secteurActivite));
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }
}
