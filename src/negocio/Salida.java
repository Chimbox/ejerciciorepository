/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import objetosServicio.Fecha;
import java.util.Objects;

/**
 *
 * @author Invitado
 */
public class Salida {

    private int id;
    private Fecha fechaHora;
    private Barco barco;
    private Destino destino;

    public Salida() {
    }

    public Salida(int id) {
        this.id = id;
    }

    public Salida(Fecha fechaHora, Barco barco, Destino destino) {
        this.fechaHora = fechaHora;
        this.barco = barco;
        this.destino = destino;
    }

    public Salida(int id, Fecha fechaHora, Barco barco, Destino destino) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.barco = barco;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fecha getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Fecha fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.fechaHora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salida other = (Salida) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Salida{" + "fechaHora=" + fechaHora + ", barco=" + barco + ", destino=" + destino + '}';
    }
}
