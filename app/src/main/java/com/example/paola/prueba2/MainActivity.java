package com.example.paola.prueba2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import com.example.paola.prueba2.model.Cliente;
import com.example.paola.prueba2.model.DaoSession;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DaoSession daoSession;

    @BindView(R.id.Et_nombre) EditText EtNombre;
    @BindView(R.id.Et_apellido) EditText EtApellido;
    @BindView(R.id.Bt_aceptar) Button BtAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        Log.i(TAG,"Numero de clientes: "+b);

    }

    @OnClick(R.id.Bt_aceptar)
    public void onViewClicked() {

    }
}
