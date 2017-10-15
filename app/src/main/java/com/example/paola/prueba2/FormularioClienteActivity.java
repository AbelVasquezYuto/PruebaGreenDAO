package com.example.paola.prueba2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.paola.prueba2.model.Cliente;
import com.example.paola.prueba2.model.DaoSession;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormularioClienteActivity extends AppCompatActivity {

    private static final String TAG = "FormularioClienteActivity";

    @BindView(R.id.Et_nombre) EditText EtNombre;
    @BindView(R.id.Et_apellido) EditText EtApellido;
    @BindView(R.id.Bt_accion) Button BtAccion;
    @BindView(R.id.rv_cliente) RecyclerView rvClientes;

    private DaoSession daoSession;

    private Cliente cliente;
    private boolean esActualizable=false;

    public static void start(Context context, Cliente cliente) {
        Intent starter = new Intent(context, FormularioClienteActivity.class);
        starter.putExtra("cliente", cliente);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        cliente = getIntent().getParcelableExtra("cliente");

        SesionDAO application = (SesionDAO) getApplication();
        daoSession = application.getDaoSession();

        if(cliente!=null){
            EtNombre.setText(cliente.getNombre());
            EtApellido.setText(cliente.getApellidos());
            BtAccion.setText("EDITAR");
            esActualizable = true;
        }else{
            BtAccion.setText("AGREGAR");
        }



        /*
        SesionDAO sesionDAO = (SesionDAO)getApplication();
        daoSession = sesionDAO.getDaoSession();

        //Registrando
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Abel");
        cliente1.setApellidos("Vasquez");

        daoSession.getClienteDao().insert(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Aldo");
        cliente2.setApellidos("Perez");

        daoSession.getClienteDao().insert(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Juan");
        cliente3.setApellidos("Gomez");

        daoSession.getClienteDao().insert(cliente3);

        Long a = daoSession.getClienteDao().count();
        Log.i(TAG,"Numero de clientes "+a);

        List<Cliente> clientes = daoSession.getClienteDao().loadAll();
        for (Cliente c: clientes){
            Log.i(TAG,c.toString());
        }

        daoSession.getClienteDao().delete(cliente3);
        Long b = daoSession.getClienteDao().count();
        Log.i(TAG,"Numero de clientes: "+b);*/

    }

    @OnClick(R.id.Bt_accion)
    public void onViewClicked() {
        if(esActualizable){
            actualizarCliente();
        }else{
            agregarCliente();
        }
    }

    private void actualizarCliente(){
        cliente.setNombre(EtNombre.getText().toString());
        cliente.setApellidos(EtApellido.getText().toString());

        daoSession.getClienteDao().update(cliente);
        Toast.makeText(this, "El cliente se actualizo correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre(EtNombre.getText().toString());
        cliente.setApellidos(EtApellido.getText().toString());

        daoSession.getClienteDao().insert(cliente);
        Toast.makeText(this, "El cliente se registro correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
