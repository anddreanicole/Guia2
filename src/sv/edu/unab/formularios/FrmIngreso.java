package sv.edu.unab.formularios;

import sv.edu.unab.dominio.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FrmIngreso {
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxtFechaNacimiento;
    private JTextArea atxtDireccion;
    private JTextField txtArea;
    private JComboBox cboGenero;
    private JTextField txtPuesto;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JTable tblEmpleados;
    private JButton btnGuardar;
    private JTextField txtId;
    private int cod=3;
    private String codi=null;
    public JPanel pnlInicio;

    //</editor-fold>
    private List<Empleado> empleadosModel;
    String Codigo;
    String Nombre;
    String ApellidoPaterno;
    String ApellidoMaterno;
    String Direccion;
    LocalDate FechaNacimiento;
    String Puesto;
    Character Sexo;
    Long Edad;
    ArrayList<Empleado>empleados= new ArrayList<>();
    DefaultTableModel modelo=new DefaultTableModel();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM//yyyy");

    public FrmIngreso() {
        initComponents();
    }




    public void initComponents() {


        tblEmpleados.setFillsViewportHeight(true);
        if (empleadosModel == null) {
            empleadosModel = new ArrayList<>();
        }
        empleadosModel.add(new Empleado("1", "Andrea Nicole", "Cabañas", "Menjivar", LocalDate.of(1999, 1, 10), "Chalatenango", "Gerente", 'F'));
        empleadosModel.add(new Empleado("2", "Antony David", "Duarte", "Perlera", LocalDate.of(1997, 11, 5), "Nueva Concepcion", "Cajero", 'M'));
        empleadosModel.add(new Empleado("3", "Pablo Eliseo", "Hernandez", "Sales", LocalDate.of(1992, 1, 25), "Concepcion Quezaltepque", "Jefe", 'M'));
        actualizarDatos(empleadosModel);
        //Agregando eventos a botones


        btnAgregar.addActionListener(evt -> {
            Empleado empleado = new Empleado();
            cod += +1;
            Empleado emp = new Empleado();
            empleado.setCodigo(String.valueOf(cod));
            empleado.setNombre(txtNombre.getText());
            empleado.setApellidoPaterno(txtApellidoPaterno.getText());
            empleado.setApellidoMaterno(txtApellidoMaterno.getText());
            DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            empleado.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText(), dft));
            empleado.setDireccion(atxtDireccion.getText());
            empleado.setPuesto(txtPuesto.getText());
            empleado.setGenero(cboGenero.getSelectedItem().toString().toUpperCase().charAt(0));
            empleadosModel.add(empleado);

            actualizarDatos(empleadosModel);

            limpiarComponentes();
        });

        btnEditar.addActionListener(e -> {

            txtNombre.setText(Nombre);
            txtApellidoPaterno.setText(ApellidoPaterno);
            txtApellidoMaterno.setText(ApellidoMaterno);
            atxtDireccion.setText(Direccion);
            txtPuesto.setText(Puesto);
          /*codi= txtId.getText();
             Predicate<Empleado> em = a -> a.getCodigo().equals(codi);
            empleadosModel.stream().filter(em).forEach(emp ->
            {
                txtNombre.setText(emp.getNombre());
                txtApellidoPaterno.setText(emp.getApellidoPaterno());
                txtApellidoMaterno.setText(emp.getApellidoMaterno());
                atxtDireccion.setText(emp.getDireccion());
                //ftxtFechaNacimiento.setText(String.valueOf(LocalDate.parse(emp.getFechaNacimiento().toString(),dtf)));
                txtPuesto.setText(emp.getPuesto());
                cboGenero.setSelectedItem(emp.getGenero());



            });*/


        });

        btnGuardar.addActionListener(e -> {
            empleadosModel.stream().forEach(c -> {
                if (c.getCodigo().equals(Codigo)) {
                    c.setNombre(txtNombre.getText());
                    c.setApellidoPaterno(txtApellidoPaterno.getText());
                    c.setApellidoMaterno(txtApellidoMaterno.getText());
                    c.setDireccion(atxtDireccion.getText());
                    c.setPuesto(txtPuesto.getText());
                    c.setGenero(cboGenero.getSelectedItem().toString().charAt(0));
                }
                limpiarComponentes();
                actualizarDatos(empleadosModel);
            });
            /*codi= txtId.getText();
            empleadosModel.stream().filter(x -> x.getCodigo().equals(codi)).forEach(empleado ->{

                    empleado.setNombre(txtNombre.getText());
                    empleado.setApellidoPaterno(txtApellidoPaterno.getText());
                    empleado.setApellidoMaterno(txtApellidoMaterno.getText());
                    empleado.setDireccion(atxtDireccion.getText());
                    empleado.setPuesto(txtPuesto.getText());
                    //empleado.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText(),dtf));
                    empleado.setGenero(cboGenero.getSelectedItem().toString().charAt(0));


                actualizarDatos(empleadosModel);

                //limpiarComponentes();
            });*/

        });

        btnEliminar.addActionListener(e -> {

            empleadosModel.removeIf(c -> c.getCodigo().equals(Codigo));
            actualizarDatos(empleadosModel);
            //Codigo = txtId.getText();
            //codi= txtId.getText();
            //Predicate<Empleado> em = a -> a.getCodigo().equals(codi);
            //empleadosModel.removeIf(em);
            //actualizarDatos(empleadosModel);

        });

        btnConsultar.addActionListener(evt -> {
            List<Empleado> empleadosFiltrados = empleadosModel.stream().filter(e -> {
                boolean encontrado = e.getNombre().toUpperCase().contains(txtNombre.getText().toUpperCase());
                if (!txtApellidoPaterno.getText().isEmpty()) {
                    encontrado = encontrado && e.getApellidoPaterno().toUpperCase().contains(txtApellidoPaterno.getText().toUpperCase());
                }
                if (!txtPuesto.getText().isEmpty()) {
                    encontrado = encontrado && e.getPuesto().contains(txtPuesto.getText());
                }
                return encontrado;
            }).collect(Collectors.toList());
            actualizarDatos(empleadosFiltrados);
        });

        /*tblEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i=tblEmpleados.getSelectedRow();
                Codigo= (tblEmpleados.getValueAt(i,0).toString());
                Nombre= (tblEmpleados.getValueAt(i,1).toString());
                ApellidoPaterno= (tblEmpleados.getValueAt(i,2).toString());
                ApellidoMaterno= (tblEmpleados.getValueAt(i,3).toString());
                Direccion= (tblEmpleados.getValueAt(i,4).toString());
                FechaNacimiento= LocalDate.parse(tblEmpleados.getValueAt(i,5).toString());
                Edad = (tblEmpleados.getValueAt(i,6).toString());
                Puesto= (tblEmpleados.getValueAt(i,5).toString());

                Sexo= (tblEmpleados.getValueAt(i,7).toString().charAt(0));
            }
        });*/
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            ftxtFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(mascara));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tblEmpleados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tblEmpleados.getSelectedRow();
                Codigo = (tblEmpleados.getValueAt(i, 0).toString());
                Nombre = (tblEmpleados.getValueAt(i, 1).toString());
                ApellidoPaterno = (tblEmpleados.getValueAt(i, 2).toString());
                ApellidoMaterno = (tblEmpleados.getValueAt(i, 3).toString());
                //FechaNacimiento= LocalDate.parse(tblEmpleados.getValueAt(i,4).toString());
                Direccion = (tblEmpleados.getValueAt(i, 5).toString());
                //FechaNacimiento= LocalDate.parse(tblEmpleados.getValueAt(i,5).toString());

                Puesto = (tblEmpleados.getValueAt(i, 6).toString());

                Sexo = (tblEmpleados.getValueAt(i, 7).toString().charAt(0));
            }


        });
    }


    private void actualizarDatos(List<Empleado> listado){
        reiniciarJTable(tblEmpleados);


        DefaultTableModel modelo  = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido P");
        modelo.addColumn("Apellido m");
        modelo.addColumn("Direecion");
        modelo.addColumn("Fecha Nacimiento");
        modelo.addColumn("Edad");

        modelo.addColumn("Puesto");
        modelo.addColumn("Sexo");

        listado.stream().forEach(empleado -> {
            Codigo = empleado.getCodigo();
            Nombre= empleado.getNombre();
            ApellidoPaterno= empleado.getApellidoPaterno();
            ApellidoMaterno = empleado.getApellidoMaterno();
            Direccion= empleado.getDireccion();
            FechaNacimiento = empleado.getFechaNacimiento();
            Edad = empleado.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);

            Puesto = empleado.getPuesto();

            Sexo = empleado.getGenero();

             modelo.addRow(new Object[]{
                     Codigo, Nombre, ApellidoPaterno, ApellidoMaterno, Direccion, FechaNacimiento, Edad, Puesto, Sexo

             });




            });

        tblEmpleados.setModel(modelo);
    }

    private void reiniciarJTable(JTable jTable){

        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
            while (modelo.getRowCount() > 0){
           modelo.removeRow(0);
        }
    }

    private void limpiarComponentes(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        ftxtFechaNacimiento.setValue(null);
        atxtDireccion.setText(null);
        txtPuesto.setText(null);

        cboGenero.setSelectedIndex(0);
    }
}



