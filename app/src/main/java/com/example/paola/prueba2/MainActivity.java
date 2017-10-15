package com.example.paola.prueba2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.paola.prueba2.model.Cliente;
import com.example.paola.prueba2.model.DaoSession;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Paola on 15/10/2017.
 */

public class MainActivity extends AppCompatActivity implements ClienteAdapter.OnClienteItemClickListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv_cliente) RecyclerView rvClientes;

    private List<Cliente> clientes;
    private ClienteAdapter adapter;

    private DaoSession daoSession;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SesionDAO application = (SesionDAO) getApplication();
        daoSession = application.getDaoSession();

        /* pasar el  mainactivity que ahora tiene el comportamiento del listener.*/
        adapter = new ClienteAdapter(this);

        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        rvClientes.setAdapter(adapter);
    }

    @Override protected void onResume() {
        super.onResume();
        clientes = daoSession.getClienteDao().loadAll();
        adapter.addList(clientes);
    }

    @OnClick(R.id.Bt_Agregar)
    public void onViewClicked() {
        FormularioClienteActivity.start(this, null);
    }

    @Override
    public void onItemClick(Cliente cliente) {
        Log.v(TAG, "usted ha clickado el item");
        daoSession.delete(cliente);
        Toast.makeText(this, "Cliene eliminado", Toast.LENGTH_SHORT).show();
        clientes = daoSession.getClienteDao().loadAll();
        adapter.addList(clientes);
    }

    @Override
    public void onEditarClienteClick(Cliente cliente) {
        FormularioClienteActivity.start(this, cliente);
    }
}
