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
        Paciente p1 = new Paciente(1L, "Juan Pérez", "1234567890", "EPS Sura", "O+");

        System.out.println(p1.toString());
    }
}
