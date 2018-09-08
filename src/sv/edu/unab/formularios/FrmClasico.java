package sv.edu.unab.formularios;

import sv.edu.unab.dominio.Empleado;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class FrmClasico {
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxtFechaNacimiento;
    private JTextArea atxtDireccion;
    private JComboBox cboGenero;
    private JTextField txtPuesto;
    private JTextField txtId;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JButton btnGuardar;
    private JTable tblEmpleados;
    public  JPanel pnlPrincipal;


    ArrayList<Empleado> listado=new ArrayList<>();
    ArrayList<Empleado> Filtrado=new ArrayList();
    String Codigo;
    String Nombre;
    String ApellidoPaterno;
    String ApellidoMaterno;
    String Direccion;
    LocalDate FechaNacimiento;
    String Puesto;
    Character Sexo;
    Long Edad;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM//yyyy");

    public FrmClasico(){
        initcomponents();
        tblEmpleados.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int i=tblEmpleados.getSelectedRow();
                Codigo= (tblEmpleados.getValueAt(i,0).toString());
                Nombre= (tblEmpleados.getValueAt(i,1).toString());
                ApellidoPaterno= (tblEmpleados.getValueAt(i,2).toString());
                ApellidoMaterno= (tblEmpleados.getValueAt(i,3).toString());
                //FechaNacimiento= LocalDate.parse(tblEmpleados.getValueAt(i,4).toString());
                Direccion= (tblEmpleados.getValueAt(i,5).toString());
                //FechaNacimiento= LocalDate.parse(tblEmpleados.getValueAt(i,5).toString());

                Puesto= (tblEmpleados.getValueAt(i,6).toString());

                Sexo= (tblEmpleados.getValueAt(i,7).toString().charAt(0));
            }
        });


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado em =new Empleado();
                em.setCodigo(String.valueOf(new Random().nextInt()));
                em.setNombre(txtNombre.getText());
                em.setApellidoPaterno(txtApellidoPaterno.getText());
                em.setApellidoMaterno(txtApellidoMaterno.getText());
                em.setDireccion(atxtDireccion.getText());
                DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                em.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText(), dft));
                em.setGenero(cboGenero.getSelectedItem().toString().charAt(0));
                listado.add(em);
                Actualizar(listado);
                limpiarcomponentes();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtNombre.setText(Nombre);
                txtApellidoPaterno.setText(ApellidoPaterno);
                txtApellidoMaterno.setText(ApellidoMaterno);
                atxtDireccion.setText(Direccion);
                ftxtFechaNacimiento.setText(String.valueOf(FechaNacimiento));
                txtPuesto.setText(Puesto);


                if (Sexo.equals('F')){
                    cboGenero.setSelectedItem(0);
                }
                else if(Sexo.equals('M')){
                    cboGenero.setSelectedItem(1);
                }
                else{
                    cboGenero.setSelectedItem(2);
                }
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<listado.size();i++){
                    if (Codigo.equals(listado.get(i).getCodigo())){
                        listado.get(i).setNombre(txtNombre.getText());
                        listado.get(i).setApellidoPaterno(txtApellidoPaterno.getText());
                        listado.get(i).setApellidoMaterno(txtApellidoMaterno.getText());
                        listado.get(i).setDireccion(atxtDireccion.getText());
                        listado.get(i).setPuesto(txtPuesto.getText());
                        //listado.get(i).setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText()));
                        listado.get(i).setGenero(cboGenero.getSelectedItem().toString().charAt(0));
                    }
                }
                Actualizar(listado);
                limpiarcomponentes();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<listado.size(); i++){
                    if(Codigo==listado.get(i).getCodigo()){
                        listado.remove(i);
                    }
                    Actualizar(listado);
                    limpiarcomponentes();
                }
            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parametroNombre=txtNombre.getText();
                Filtrado.clear();
                for (int i=0;i<listado.size();i++){
                    if (parametroNombre.equals(listado.get(i).getNombre())){
                        Empleado empl=new Empleado();
                        empl.setCodigo(listado.get(i).getCodigo());
                        empl.setNombre(listado.get(i).getNombre());
                        empl.setApellidoPaterno(listado.get(i).getApellidoPaterno());
                        empl.setApellidoMaterno(listado.get(i).getApellidoMaterno());
                        empl.setDireccion(listado.get(i).getDireccion());
                        empl.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                        empl.setPuesto(listado.get(i).getPuesto());
                        empl.setGenero(listado.get(i).getGenero());
                        Filtrado.add(empl);
                    }
                }
                Actualizar(Filtrado);
            }
        });
    }



    private void initcomponents() {

        listado.add(new Empleado ("1","Andrea Nicole", "Cabañas", "Menjivar", LocalDate.of(1999, 1, 10), "Chalatenango", "Gerente",  'F'));
        listado.add(new Empleado ("2","Antony David", "Duarte", "Perlera", LocalDate.of(1997, 11, 5), "Nueva Concepcion", "Cajero",  'M'));
        listado.add(new Empleado ("3","Pablo Eliseo", "Hernandez", "Sales", LocalDate.of(1992, 1, 25), "Concepcion Quezaltepque", "Jefe",  'M'));

        Actualizar(listado);

        try {
            MaskFormatter Mascara = new MaskFormatter("##/##/####");
            Mascara.setPlaceholderCharacter('_');
            ftxtFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(Mascara));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void Actualizar(ArrayList<Empleado> listado){
        String matriz[][] = new String[listado.size()][8];
        for(int i = 0; i<listado.size(); i++){
            matriz[i][0]= listado.get(i).getCodigo();
            matriz[i][1]= listado.get(i).getNombre();
            matriz[i][2]= listado.get(i).getApellidoPaterno();
            matriz[i][3]= listado.get(i).getApellidoMaterno();
            matriz[i][5]= listado.get(i).getDireccion();
            matriz[i][4]= String.valueOf(listado.get(i).getFechaNacimiento());
            matriz[i][6]= listado.get(i).getPuesto();
            matriz[i][7]= String.valueOf(listado.get(i).getGenero().toString().charAt(0));
        }
        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]
                {"Codigo","Nombre","Apellido Paterno","Apellido Materno","Fecha Nacimiento","Direccion","Puesto","Tipo"}));

        //EdadPromedio();

    }


    public void limpiarcomponentes(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        atxtDireccion.setText(null);
        txtPuesto.setText(null);
        cboGenero.setSelectedItem(0);
    }


}




