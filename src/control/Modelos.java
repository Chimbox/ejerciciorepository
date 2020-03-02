/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import negocio.Barco;
import negocio.Destino;
import negocio.Salida;
import negocio.Socio;

/**
 *
 * @author Invitado
 */
public class Modelos {

    public static DefaultTableModel socioTableModel(List<Socio> lstSocios) {
        Object tabla[][];
        String[] nombresCols = {"ID", "DNI", "Nombre", "Direccion"};
        if (lstSocios != null) {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabla = new Object[lstSocios.size()][nombresCols.length];

            for (int i = 0; i < lstSocios.size(); i++) {
                Socio socio = lstSocios.get(i);
                tabla[i][0] = socio.getId();
                tabla[i][1] = socio.getDni();
                tabla[i][2] = socio.getNombre();
                tabla[i][3] = socio.getDireccion();
            }
            modelo.setDataVector(tabla, nombresCols);
            return modelo;
        }
        return null;
    }

    public static DefaultTableModel barcoTableModel(List<Barco> lstBarcos) {
        Object tabla[][];
        String[] nombresCols = {"Num. Matrícula", "Nombre", "Num. Amarre", "Cuota", "Socio"};
        if (lstBarcos != null) {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabla = new Object[lstBarcos.size()][nombresCols.length];

            for (int i = 0; i < lstBarcos.size(); i++) {
                Barco barco = lstBarcos.get(i);
                tabla[i][0] = barco.getNumMatricula();
                tabla[i][1] = barco.getNombre();
                tabla[i][2] = barco.getNumAmarre();
                tabla[i][3] = "$" + barco.getCuota();
                tabla[i][4] = barco.getSocio().getNombre();
            }
            modelo.setDataVector(tabla, nombresCols);
            return modelo;
        }
        return null;
    }

    public static DefaultTableModel destinoTableModel(List<Destino> lstDestinos) {
        Object tabla[][];
        String[] nombresCols = {"ID", "Nombre"};
        if (lstDestinos != null) {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabla = new Object[lstDestinos.size()][nombresCols.length];

            for (int i = 0; i < lstDestinos.size(); i++) {
                Destino destino = lstDestinos.get(i);
                tabla[i][0] = destino.getId();
                tabla[i][1] = destino.getNombre();
            }
            modelo.setDataVector(tabla, nombresCols);
            return modelo;
        }
        return null;
    }

    public static DefaultTableModel salidaTableModel(List<Salida> lstSalidas) {
        Object tabla[][];
        String[] nombresCols = {"ID", "Fecha", "Hora", "Barco", "Destino"};
        if (lstSalidas != null) {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabla = new Object[lstSalidas.size()][nombresCols.length];

            for (int i = 0; i < lstSalidas.size(); i++) {
                Salida salida = lstSalidas.get(i);
                tabla[i][0] = salida.getId();
                tabla[i][1] = salida.getFechaHora();
                tabla[i][2] = salida.getFechaHora().getCadenaHora();
                tabla[i][3] = salida.getBarco();
                tabla[i][4] = salida.getDestino();
            }
            modelo.setDataVector(tabla, nombresCols);
            return modelo;
        }
        return null;
    }

    public static DefaultComboBoxModel sociosComboBoxModel(List<Socio> lstSocios) {
        DefaultComboBoxModel<Socio> comboSocio = new DefaultComboBoxModel<>();

        if (lstSocios != null) {
            for (Socio socio : lstSocios) {
                comboSocio.addElement(socio);
            }
            return comboSocio;
        }
        return null;
    }

    public static DefaultComboBoxModel destinosComboBoxModel(List<Destino> lstDestinos) {
        DefaultComboBoxModel<Destino> comboDestino = new DefaultComboBoxModel<>();

        if (lstDestinos != null) {
            for (Destino destino : lstDestinos) {
                comboDestino.addElement(destino);
            }
            return comboDestino;
        }
        return null;
    }

    public static DefaultComboBoxModel barcosComboBoxModel(List<Barco> lstBarcos) {
        DefaultComboBoxModel<Barco> comboBarco = new DefaultComboBoxModel<>();

        if (lstBarcos != null) {
            for (Barco barco : lstBarcos) {
                comboBarco.addElement(barco);
            }
            return comboBarco;
        }
        return null;
    }
}
