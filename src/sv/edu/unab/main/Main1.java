package sv.edu.unab.main;

import sv.edu.unab.formularios.FrmClasico;

import javax.swing.*;
import java.awt.*;

public class Main1 {
    public static void main(String[] args) {
        Dimension resolucionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //Ajustando tamaño del formulario
        Dimension ajusteTamaño = new Dimension(resolucionPantalla.width/2,resolucionPantalla.height - 100);
        JFrame frm = new JFrame("Empleados Express Business");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FrmClasico().pnlPrincipal);
        frm.setPreferredSize(ajusteTamaño);
        frm.setResizable(false);
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}
