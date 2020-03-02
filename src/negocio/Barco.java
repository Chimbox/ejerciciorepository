/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author lv1013
 */
public class Barco {

    private int numMatricula;
    private String nombre;
    private short numAmarre;
    private double cuota;
    private Socio socio;

    public Barco() {
    }

    public Barco(int numMatricula) {
        this.numMatricula = numMatricula;
    }

    public Barco(int numMatricula, String nombre, short numAmarre, double cuota, Socio socio) {
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.numAmarre = numAmarre;
        this.cuota = cuota;
        this.socio = socio;
    }

    public int getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(int numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getNumAmarre() {
        return numAmarre;
    }

    public void setNumAmarre(short numAmarre) {
        this.numAmarre = numAmarre;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
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
        final Barco other = (Barco) obj;
        if (this.numMatricula != other.numMatricula) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.numMatricula;
        return hash;
    }

    @Override
    public String toString() {
        return nombre + " con matrícula " + numMatricula;
    }
}
