package com.example.paola.prueba2;
import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.paola.prueba2.model.Cliente;

import java.util.List;

/**
 * Created by Paola on 14/10/2017.
 */
//Paso 5 se hereda del recyclerview.adpater
    //Paso 6 se generan los metodos 3
public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{

    //Paso 7
    private List<Cliente> clientes;

    private OnClienteItemClickListener onClienteItemClick;

    public ClienteAdapter(OnClienteItemClickListener onClienteItemClick){
        this.onClienteItemClick = onClienteItemClick;
    }

    //Paso 7
    public void addList(List<Cliente> clientes){
        this.clientes=clientes;
        notifyDataSetChanged();//si el adapter recibe una lista, el recyclerview se actualiza.
    }



    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Paso 9
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,parent,    false);


        return new ClienteViewHolder(view);
    }

    //carga los datos
    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {

        //Paso 10
        final Cliente cliente = clientes.get(position);
        holder.tvName.setText(cliente.getNombre());
        holder.tvLastName.setText(cliente.getApellidos());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClienteItemClick!=null)
                    onClienteItemClick.onItemClick(cliente);
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClienteItemClick!=null)
                    onClienteItemClick.onEditarClienteClick(cliente);
            }
        });

    }

    //retorna cantidad de items de a lista
    @Override
    public int getItemCount() {
        //Paso 8
        return clientes==null?0:clientes.size();
    }

    //Paso 1
    class ClienteViewHolder extends RecyclerView.ViewHolder{

        //Paso 3 las vistas de tu activity_main_item
        TextView tvName,tvLastName;
        Button btnEditar;

        //Paso 2
        public ClienteViewHolder(View itemView) {
            super(itemView);
            //Paso 4 se instancia
            tvName = itemView.findViewById(R.id.tv_name);
            tvLastName = itemView.findViewById(R.id.tv_lastname);
            btnEditar = itemView.findViewById(R.id.Btn_editar);

        }
    }

    public interface OnClienteItemClickListener{
        void onItemClick(Cliente cliente); //metodo para eliminar un item
        void onEditarClienteClick(Cliente cliente); //metodo para click al boton editar
    }



}
