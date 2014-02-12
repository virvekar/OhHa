/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tienhoitopeli.Sovelluslogiikka.ReitinLukija;

/**
 *Maaraa mita tapahtuu, kun nuolinappaimia painetaan
 * Liikuttaa aurausautoa ja tallentaa reitin.
 * @author virvemaa
 */
public class NappaimistonKuuntelija implements KeyListener {

    private AurausAuto auto;
    private Component piirtoAlusta;
    private ReitinLukija reitinLukija;

    public NappaimistonKuuntelija(AurausAuto aurausAuto, Component piirtoAlusta, ReitinLukija reitinLukija) {
        this.auto = aurausAuto;
        this.piirtoAlusta = piirtoAlusta;
        this.reitinLukija = reitinLukija;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.reitinLukija.LiikuVasemmalle()) {
                this.auto.Siirry(-this.auto.getYKoko(), 0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.reitinLukija.LiikuOikealle()) {
                this.auto.Siirry(this.auto.getYKoko(), 0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (this.reitinLukija.LiikuYlos()) {
                this.auto.Siirry(0, -this.auto.getYKoko());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.reitinLukija.LiikuAlas()) {
                this.auto.Siirry(0, this.auto.getYKoko());
            }
        }
        this.piirtoAlusta.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }
    public void VaihdaReitinLukija(ReitinLukija reitinLukija){
        this.reitinLukija=reitinLukija;
    }
}
