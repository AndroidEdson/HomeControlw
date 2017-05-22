package com.example.pch61m.homecontrol;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.pch61m.homecontrol.home.db.Inventory;
import com.example.pch61m.homecontrol.home.db.InventoryHelper;
import com.example.pch61m.homecontrol.home.db.Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentExterior.OnFragmentInteractionListener,FragmentExterior.OnColorChangeListener,FragmentInterior.OnFragmentInteractionListener,FragmentInterior.OnColorChangeListener,FragmentRoom1.OnFragmentInteractionListener,FragmentRoom1.OnColorChangeListener,
        FragmentRoom2.OnFragmentInteractionListener,FragmentRoom2.OnColorChangeListener{


//SAUL SE LA COME


    private static final int REQUEST_ENABLE_BT = 1;
    private static final UUID SERIAL_PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

    private BtDevRvAdapter adapter;
    private List<BluetoothDevice> devices;
    private BluetoothSocket connectedSocket;
    ProgressDialog progress_search=null;


    private EditText txtState;
    private EditText txtMessages;
    private EditText txtToSend;
    DataFromActivityToFragment dataFromActivityToFragment;

    // VARIABLES DE LECTURAS
    public String recibido;
    public String LM1;
    public String LM2;
    public String LM3;
    public String LM4;
    public String P1;
    public String P2;
    public String V1;
    public String V2;
    public String T1;
    public String PI;
    public String Z1;
    public String R1;
    public String B1;
    public String p1;
    public String p2;
    public String v1;
    public String v2;
    public String pi;

    // VARIABLES DE LECTURAS


  //FUNCIONES QUE VIENEN DE LOS FRAGMENTS PARA INTERCAMBIAR DATOSs



    @Override
    public void L1(String value) {

        SendMessage(value);
        }

        @Override
        public void L2(String value) {
            SendMessage(value);

        }

        @Override
        public void S1(String value) {
            SendMessage(value);
        }

        @Override
        public void S2(String value) {
            SendMessage(value);
        }
    @Override
    public void B1(String value) {
        SendMessage(value);
    }
    @Override
    public void T1(String value) {
        SendMessage(value);
    }
    @Override
    public void R1(String value) {
        SendMessage(value);
    }
    public void B2(String value) {
        SendMessage(value);
    }
    @Override
    public void T2(String value) {
        SendMessage(value);
    }
    @Override
    public void R2(String value) {
        SendMessage(value);
    }
    @Override
    public void buzzerconfig(String value) {
        SendMessage(value);

    }



    //LECTURA
    @Override
    public String LM1() {
        return LM1;
    }
    @Override
    public String LM2() {
        return LM2;
    }
    @Override
    public String LM3() {
        return LM3;
    }
    @Override
    public void UpdateA1(String value) {
        SendMessage(value);
    }

       @Override
       public String LM4() {
        return LM4;
      }

    @Override
    public String P1() {
        return P1;
    }
    @Override
    public String P2() {
        return P2;
    }
    @Override
    public String PI() {
        return PI;
    }
    @Override
    public String V1() {
        return V1;
    }
    @Override
    public String V2() {
        return V2;
    }
    @Override
    public String Z1() {
        return Z1;
    }
    @Override
    public String p1() {
        return p1;
    }
    @Override
    public String p2() {
        return p2;
    }
    @Override
    public String v1() {
        return v1;
    }
    @Override
    public String v2() {
        return v2;
    }
    @Override
    public String pi() {
        return pi;
    }

    //LECTURA


    //LECTURA DE DATOS



    //END FUNCIONES QUE VIENEN DE LOS FRAGMENTS PARA INTERCAMBIAR DATOS


    // ************************************************
    // BtBackgroundTask
    // ************************************************

    private class BtBackgroundTask extends AsyncTask<BufferedReader, String, Void> {
        @Override
        protected Void doInBackground(BufferedReader... params) {
            try {
                while (!isCancelled()) {
                    publishProgress(params[0].readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].startsWith("LM1")){
                LM1=values[0];

            }
            if(values[0].startsWith("LM2")){
                LM2=values[0];
            }
            if(values[0].startsWith("LM3")){
                LM3=values[0];
            }
            if(values[0].startsWith("LM4")){
                LM4=values[0];
            }
            if(values[0].startsWith("P1")){
                P1=values[0];
            }
            if(values[0].startsWith("P2")){
                P2=values[0];
            }
            if(values[0].startsWith("V1")){
                V1=values[0];
            }
            if(values[0].startsWith("V2")){
                V2=values[0];
            }
            if(values[0].startsWith("PI")){
                PI=values[0];
            }
            if(values[0].startsWith("Z1")){
                Z1=values[0];
            }
            if(values[0].startsWith("p1")){
                p1=values[0];
            }
            if(values[0].startsWith("p2")){
                p2=values[0];
            }
            if(values[0].startsWith("v1")){
                v1=values[0];
            }
            if(values[0].startsWith("v2")){
                v2=values[0];
            }
            if(values[0].startsWith("pi")){
                pi=values[0];
            }

            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
           // appendMessageText("[Recibido] " + values[0]);
        }
    }


    // ************************************************
    // ViewHolder for RecyclerView
    // ************************************************

    private class BtDevRvHolder extends RecyclerView.ViewHolder {
        private final TextView lblName;
        private final TextView lblAddress;

        private BluetoothDevice device;

        BtDevRvHolder(View itemView) {
            super(itemView);

            lblName = (TextView) itemView.findViewById(R.id.device_name);
            lblAddress = (TextView) itemView.findViewById(R.id.device_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(MainActivity.this, lblName);
                    popup.getMenuInflater().inflate(R.menu.device_popup, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.connect_menu_item:
                                    // Use a temporary socket until correct connection is done
                                    BluetoothSocket tmpSocket = null;

                                    // Connect with BluetoothDevice
                                    if (connectedSocket == null) {
                                        try {
                                            tmpSocket = device.createRfcommSocketToServiceRecord(MainActivity.SERIAL_PORT_UUID);

                                            // Get device's own Bluetooth adapter
                                            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

                                            // Cancel discovery because it otherwise slows down the connection.
                                            btAdapter.cancelDiscovery();

                                            // Connect to the remote device through the socket. This call blocks until it succeeds or throws an exception
                                            tmpSocket.connect();

                                            // Acknowledge connected socket
                                            connectedSocket = tmpSocket;

                                            // Create socket reader thread
                                            BufferedReader br = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
                                            new BtBackgroundTask().execute(br);
                                            Toast.makeText(getApplicationContext(), "Conectado ", Toast.LENGTH_SHORT).show();

                                           // appendStateText("[Estado] Conectado.");
                                        } catch (IOException e) {
                                            try {
                                                if (tmpSocket != null) {
                                                    tmpSocket.close();
                                                }
                                            } catch (IOException closeExceptione) {
                                            }
                                            Toast.makeText(getApplicationContext(), "No se pudo establecer conexión! ", Toast.LENGTH_SHORT).show();

                                           // appendStateText("[Error] No se pudo establecer conexión!");
                                            e.printStackTrace();
                                        }
                                    }
                                    break;

                                default:
                                    break;
                            }

                            return true;
                        }
                    });

                    popup.show();
                }
            });
        }

        void bind(BluetoothDevice device) {
            this.device = device;
            lblName.setText(device.getName());
            lblAddress.setText(device.getAddress());
        }
    }


    // ************************************************
    // Adapter for RecyclerView
    // ************************************************

    private class BtDevRvAdapter extends RecyclerView.Adapter<BtDevRvHolder> {
        private List<BluetoothDevice> devices;

        BtDevRvAdapter(List<BluetoothDevice> devices) {
            this.devices = devices;
        }

        @Override
        public BtDevRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new BtDevRvHolder(inflater.inflate(R.layout.device_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(BtDevRvHolder holder, int position) {
            holder.bind(devices.get(position));
        }

        @Override
        public int getItemCount() {
            return devices.size();
        }

        void update() {
            notifyDataSetChanged();
        }
    }


    // ************************************************
    // MainActivity implementation
    // ************************************************

    // Create a BroadcastReceiver for ACTION_STATE_CHANGED
    private final BroadcastReceiver btStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(intent.getAction())) {
                // Bluetooth adapter state has changed
                switch (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(getApplicationContext(), "Bluetooth Apagado ", Toast.LENGTH_SHORT).show();
                        //  appendStateText("[Estado] Apagado.");
                        break;

                    case BluetoothAdapter.STATE_ON:
                     //   progress_search.dismiss();

                        Toast.makeText(getApplicationContext(), "Bluetooth Encendido ", Toast.LENGTH_SHORT).show();

                        // appendStateText("[Estado] Encendido.");
                        break;

                    case BluetoothAdapter.STATE_TURNING_OFF:
                      //  appendStateText("[Acción] Apagando...");
                        break;

                    case BluetoothAdapter.STATE_TURNING_ON:

                      //  appendStateText("[Acción] Encendiendo...");
                        break;
                }
            }
        }
    };

    // Create a BroadcastReceiver for ACTION_DISCOVERY_STARTED
    private final BroadcastReceiver btDiscoveryStartedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(intent.getAction())) {

                progress_search = new ProgressDialog(MainActivity.this);
                progress_search.setMessage("Iniciando Busqueda...");
                progress_search.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress_search.show();

               // appendStateText("[Acción] Iniciando búsqueda de dispositivos...");
            }
        }
    };

    // Create a BroadcastReceiver for ACTION_DISCOVERY_FINISHED
    private final BroadcastReceiver btDiscoveryFinishedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction())) {


                if(devices.isEmpty())
                {

                    Toast.makeText(getApplicationContext(), "No se encontraron dispositivos ", Toast.LENGTH_SHORT).show();
                }


              //  appendStateText("[Acción] Finalizando búsqueda de dispositivos...");
                progress_search.dismiss();

            }
        }
    };

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver btFoundReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                // Discovery has found a device. Get the BluetoothDevice object and its info from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                devices.add(device);

                adapter.update();
                Toast.makeText(getApplicationContext(), "Dispositivo: " + device.getName() + " encontrado", Toast.LENGTH_SHORT).show();

              //  appendStateText("[Info] Dispositivo encontrado: " + device.getName() + ".");
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get device's own Bluetooth adapter

        switch (item.getItemId()) {
            case R.id.menu_item_paired:
                devices.clear();
                adapter.update();

                if (id == R.id.action_settings) {
                    return true;
                }

                // Get paired devices
              //  appendStateText("[Acción] Buscando dispositivos sincronizados...");
                Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();

                progress_search = new ProgressDialog(MainActivity.this);
                progress_search.setMessage("Dispositivos Emparejados...");
                progress_search.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress_search.show();
                // Check if there are paired devices
                if (pairedDevices.size() > 0) {
                    if (pairedDevices.size() == 1) {
                    //    appendStateText("[Info] Se encontró 1 dispositivo.");
                    } else {
                        Toast.makeText(getApplicationContext(), "Se encontraron " + pairedDevices.size() + " dispositivos.", Toast.LENGTH_SHORT).show();

                        // appendStateText("[Info] Se encontraron " + pairedDevices.size() + " dispositivos.");
                    }

                    // Loop through paired devices
                    for (BluetoothDevice device : pairedDevices) {
                        devices.add(device);
                      //  appendStateText("[Info] Dispositivo sincronizado: " + device.getName() + ".");
                    }

                    adapter.update();
                    progress_search.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), " No se encontraron dispositivos sincronizados.", Toast.LENGTH_SHORT).show();

                    // appendStateText("[Info] No se encontraron dispositivos sincronizados.");
                }
                return true;

            case R.id.menu_item_discover:
                // Check if device supports Bluetooth
                if (btAdapter == null) {
                   // appendStateText("[Error] Dispositivo Bluetooth no encontrado!");
                }
                // Check if device adapter is not enabled
                else if (!btAdapter.isEnabled()) {
                    // Issue a request to enable Bluetooth through the system settings (without stopping application)
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }
                // Start discovery process
                else {
                    devices.clear();
                    adapter.update();
                    btAdapter.startDiscovery();
                }
                return true;

            case R.id.menu_item_disconnect:
                if (connectedSocket != null) {
                    try {
                        connectedSocket.close();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), " Ocurrió un problema al intentar cerrar la conexión!", Toast.LENGTH_SHORT).show();
                      //  appendStateText("[Error] Ocurrió un problema al intentar cerrar la conexión!");
                        e.printStackTrace();
                    } finally {
                        connectedSocket = null;
                        Toast.makeText(getApplicationContext(), " Desconectado", Toast.LENGTH_SHORT).show();

                        // appendStateText("[Estado] Desconectado.");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), " No hay  conexión con otro dispositivo ", Toast.LENGTH_SHORT).show();
                   // appendStateText("[Info] La conexión no parece estar activa.");
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // CODIGO ON CREATE

    public static final String EXTRA_ID = "com.example.pch61m.homecontrol.extra_id";
    private Inventory inventory;
    private int id;
    private Users users;
    private Switch blue_switch;
    private boolean flag;
    private int  request_code=0;
    Handler mHandler6 = new Handler() ;

    private View device;
    private View device1;
    private View sendMessage;
    private LinearLayout send;
    private LinearLayout linearLayout_recycler;
    private TextView decive_txt;

    int aux=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Intent i = getIntent();

        id= i.getIntExtra(EXTRA_ID,0);


        if(savedInstanceState!= null)
        {
          //  id= savedInstanceState.getString(KEY_ID, "");

        }




        inventory= new Inventory(getApplicationContext());
        InventoryHelper.backupDatabaseFile(getApplicationContext());

        users= inventory.getUserFromID(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        device= (View)findViewById(R.id.layout_device);
        device1= (View)findViewById(R.id.layout_device1);
        sendMessage= (View)findViewById(R.id.view_send);
        send= (LinearLayout)findViewById(R.id.layout_send);
        linearLayout_recycler= (LinearLayout)findViewById(R.id.layout_recycler);
        decive_txt= (TextView)findViewById(R.id.devices_list_label) ;

        send.setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);

        TextView email_user = (TextView)hView.findViewById(R.id.textView_email);
        TextView name_user = (TextView)hView.findViewById(R.id.textView_name);

        email_user.setText(users.getEmail());
        name_user.setText(users.getName() +" "+ users.getLastname());
        navigationView.setNavigationItemSelectedListener(this);

        //INICIO DE BLUETOOTH


        RecyclerView rvDevices = (RecyclerView) findViewById(R.id.devices_list);
        rvDevices.setLayoutManager(new LinearLayoutManager(this));

        devices = new ArrayList<>();
        adapter = new BtDevRvAdapter(devices);
        rvDevices.setAdapter(adapter);


        // Setup message-to-send edit-text
        txtToSend = (EditText) findViewById(R.id.message_to_send_text);

        Button btnSend = (Button) findViewById(R.id.send_button);
         blue_switch = (Switch) findViewById(R.id.blue_switch);


        if (!btAdapter.isEnabled()) {
            blue_switch.setChecked(false);

        }
        else {
            blue_switch.setChecked(true);

        }



        blue_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if (blue_switch.isChecked())
             {
                 //   Toast.makeText(getApplicationContext(), "  true ", Toast.LENGTH_SHORT).show();
                 if (!btAdapter.isEnabled()) {
                     // Issue a request to enable Bluetooth through the system settings (without stopping application)
                     Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(intent, REQUEST_ENABLE_BT);
                 }

             }else{
                 // Toast.makeText(getApplicationContext(), "  false ", Toast.LENGTH_SHORT).show();
                 if (btAdapter.isEnabled()) {
                     //btAdapter.disable();
                      Intent cancel = new Intent(getApplicationContext(), Cancel_Confirmation.class);
                     startActivityForResult(cancel, request_code);

                 }
             }

            }
        });



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ... Transmisión de datos
                try {
                    if ((connectedSocket != null) && (connectedSocket.isConnected())) {
                        String toSend = txtToSend.getText().toString().trim();

                        if (toSend.length() > 0) {
                            // TBI - This object "should" be a member variable
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connectedSocket.getOutputStream()));
                            bw.write(toSend);
                            bw.write("\r\n");
                            bw.flush();
                            Toast.makeText(getApplicationContext(), "  Enviado "+ toSend, Toast.LENGTH_SHORT).show();
                           // appendMessageText("[Enviado] " + toSend);
                        }

                        txtToSend.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "  No se está conectado con algún dispositivo" , Toast.LENGTH_SHORT).show();

                        //   appendStateText("[Error] La conexión no parece estar activa!");
                    }
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), " Ocurrió un problema durante el envío de datos!" , Toast.LENGTH_SHORT).show();

                    // appendStateText("[Error] Ocurrió un problema durante el envío de datos!");
                    e.printStackTrace();
                }
            }
        });

        // Register for broadcasts when bluetooth device state changes
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(btStateReceiver, filter);

        // Register for broadcasts when bluetooth discovery state changes
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(btDiscoveryStartedReceiver, filter);

        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(btDiscoveryFinishedReceiver, filter);

        // Register for broadcasts when a device is discovered.
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(btFoundReceiver, filter);

    }


    //________________________________FUNCION DE ENVIAR _______________________________________________
    //_______________________________________________________________________________


    public void SendMessage(String texto){

    try {
        if ((connectedSocket != null) && (connectedSocket.isConnected())) {
            String toSend = texto.trim();

            if (toSend.length() > 0) {
                // TBI - This object "should" be a member variable
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connectedSocket.getOutputStream()));
                bw.write(toSend);
                bw.write("\r\n");
                bw.flush();
               // Toast.makeText(getApplicationContext(), "  Enviado "+ toSend, Toast.LENGTH_SHORT).show();
                // appendMessageText("[Enviado] " + toSend);
            }

        } else {
            Toast.makeText(getApplicationContext(), "  No se está conectado con algún dispositivo" , Toast.LENGTH_SHORT).show();
            //   appendStateText("[Error] La conexión no parece estar activa!");
        }
    } catch (IOException e) {
        Toast.makeText(getApplicationContext(), " Ocurrió un problema durante el envío de datos!" , Toast.LENGTH_SHORT).show();

        // appendStateText("[Error] Ocurrió un problema durante el envío de datos!");
        e.printStackTrace();
    }



}

    //_______________________________________________________________________________
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_ENABLE_BT) {
                    // Get device's own Bluetooth adapter
                    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

                    // Start discovery process
                    devices.clear();
                    adapter.update();
                    btAdapter.startDiscovery();
                }
                break;

            case RESULT_CANCELED:
            default:
                Toast.makeText(getApplicationContext(), "El dispositivo Bluetooth no pudo ser habilitado! " , Toast.LENGTH_SHORT).show();

                //  appendStateText("[Error] El dispositivo Bluetooth no pudo ser habilitado!");
                break;
        }

        if ( requestCode==request_code && resultCode== RESULT_OK)
        {
              btAdapter.disable();
           // Toast.makeText(getApplicationContext(),"SIMON Apagado", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the ACTION_STATE_CHANGE receiver
        unregisterReceiver(btStateReceiver);

        // Unregister the ACTION_DISCOVERY_STARTED receiver.
        unregisterReceiver(btDiscoveryStartedReceiver);

        // Unregister the ACTION_DISCOVERY_FINISHED receiver.
        unregisterReceiver(btDiscoveryFinishedReceiver);

        // Unregister the ACTION_FOUND receiver
        unregisterReceiver(btFoundReceiver);
    }

    private void appendStateText(String text) {
        txtState.setText(text + "\n" + txtState.getText());
    }

    private void appendMessageText(String text) {
        txtMessages.setText(text + "\n" + txtMessages.getText());
    }








    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

//NOOOOOOOOOOOOOOOOOOOOOOOOOTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        // SI QUIERES CREAR OTRA COMO EXTERIOR AGREGAS UN NUEVO FRAGMENT EN BLANCO  Y LO UNICO QUE TIENES QU CAMBIAR SERIA DENTRO DEL IF PONER FRAGMENT= NEW FRAGMENTNUEVO();  Y LISTO
//ddddeeff
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment= null;
        Boolean Fragment_selected=false;

        device.setVisibility(View.GONE);
        device1.setVisibility(View.GONE);
        sendMessage.setVisibility(View.GONE);
        send.setVisibility(View.GONE);
        linearLayout_recycler.setVisibility(View.GONE);
        decive_txt.setVisibility(View.GONE);



        if (id == R.id.nav_exterior) {
            // Handle the camera action
           // SendMessage("D1");
            SendMessage("E1");
           mHandler6.post(runnable1);
            mHandler6.removeCallbacks(runnable2);
            mHandler6.removeCallbacks(runnable3);
            mHandler6.removeCallbacks(runnable4);
            LM4="LM430";
           fragment = new FragmentExterior();

        } else if (id == R.id.nav_interior) {

            SendMessage("I1");
            mHandler6.post(runnable2);
            mHandler6.removeCallbacks(runnable1);
            mHandler6.removeCallbacks(runnable3);
            mHandler6.removeCallbacks(runnable4);

            LM1 = "LM100";
            P1 = "P10";
            P2 = "P20";
            V1 = "V10";
            V2 = "V20";
            PI = "PI0";
            Z1 = "Z10";
            p1 = "p10";
            p2 = "p20";
            v1 = "v10";
            v2 = "v20";
            pi = "pi0";



            fragment = new FragmentInterior();

        } else if (id == R.id.nav_room1) {
            SendMessage("H1");
            mHandler6.post(runnable3);
            mHandler6.removeCallbacks(runnable1);
            mHandler6.removeCallbacks(runnable2);
            mHandler6.removeCallbacks(runnable4);
            SendMessage("B110");
            LM2 = "LM200";
            fragment = new FragmentRoom1();

        } else if (id == R.id.nav_room2) {
            SendMessage("H2");
            mHandler6.post(runnable4);
            mHandler6.removeCallbacks(runnable2);
            mHandler6.removeCallbacks(runnable3);
            mHandler6.removeCallbacks(runnable1);
            SendMessage("B210");
            LM3 = "LM300";
            fragment = new FragmentRoom2();

        }
        else if (id == R.id.nav_users) {


            fragment = new FragmentUser();

        }
        else if (id == R.id.nav_profiles) {

        }


        if (fragment!=null){
      //   Toast.makeText(getApplicationContext(), "HOLA", Toast.LENGTH_SHORT).show();
          //  dataFromActivityToFragment = (DataFromActivityToFragment) fragment;

         FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.content_main, fragment);
         ft.commit();

     }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public interface DataFromActivityToFragment {
        void sendData(String data);
    }



    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            mHandler6.postDelayed(runnable1, 3000);
            SendMessage("D1");
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            mHandler6.postDelayed(runnable2, 3000);
            SendMessage("D2");
        }
    };
    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            mHandler6.postDelayed(runnable3, 3000);
            SendMessage("D3");
        }
    };
    Runnable runnable4 = new Runnable() {
        @Override
        public void run() {
            mHandler6.postDelayed(runnable4, 3000);
            SendMessage("D4");
        }
    };



}
