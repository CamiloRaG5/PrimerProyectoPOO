/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestioncitas;

import modelo.Paciente;

/**
 *
 * @author CARG
 */
public class GestionCitas {
    public static void main(String[] args) {
        Paciente p1 = new Paciente(1, "milo perez", "1234", "30000000", "calle");

        System.out.println(p1.toString());
    }
}
