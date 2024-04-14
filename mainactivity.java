
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 25;
    //1 declaracion de los objetos de la interface que se usaran en la parte logica
    private Button btnCheckPermissions;

    private Button btnRequestPermissions;

    private TextView tvCamera;
    private TextView tvBiometric;

    private TextView tvExternalWs;

    private TextView tvReadExternalS;

    private TextView tvInternet;

    private TextView tvResponse;

    //1.1 objetos para recursos

    private TextView versionAndroid;
    private int versionSDK;
    private ProgressBar pbLevelBatt;
    private  TextView tvLevelBatt;
    private TextView tvConexion;
    IntentFilter batFilter;
    Object cameraManager;
    String cameraId;
    private Button btnOn;

    private Button btnOff;
    ConnectivitiManager conexion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckPermissions = findViewById(R.id.btnCheckPermission);
        btnRequestPermissions = findViewById(R.id.btnRequestPermission);
        btnRequestPermissions.setEnabled(false);
        tvCamera = findViewById(R.id.tvCamera);
        tvBiometric = findViewById(R.id.tvDactilar);
        tvExternalWs = findViewById(R.id.tvEws);
        tvReadExternalS = findViewById(R.id.tvRS);
        tvInternet = findViewById(R.id.tvInternet);
        tvResponse =findViewById(R.id.tvResponse);

        btnCheckPermissions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermission();
            }
        });

        btnRequestPermissions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requestPermission();
            }
        });



        //3. llamado del metodo de enlace de objetos
        initObject();
    }






//8.implementacion


    @Override
    protected void onResume() {
        super.onResume();
        String versionSO = Build.VERSION.RELEASE;
        versionSDK = Build.VERSION.SDK_INT;
        versionAndroid.setText("version SO"+versionSO+"/ SDK:"+versionSDK);
    }
//9.encendido y apagado de linterna


    //5. cerificacion de permisos
    private void checkPermission() {
        //si hay permiso --> 0 --> si no --> -1
        int statusCamera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        int statusWES = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int statusRES = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        int statusInternet = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        int statusBiometric = ContextCompat.checkSelfPermission(this,
                Manifest.permission.USE_BIOMETRIC);

        tvCamera.setText("Status Camera:"+statusCamera);
        tvExternalWs.setText ("Status WES:" +statusWES);
        tvReadExternalS.setText("Status RES:"+statusRES);
        tvInternet.setText("Status Internet: "+statusInternet);
        tvBiometric.setText("Status Biometric:"+statusBiometric);
        btnRequestPermissions.setEnabled(true);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET,
                        Manifest.permission.USE_BIOMETRIC},
                1);
    }



    //6. solicitud de permiso de camara

    private void requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
        }
    }

    private void requestWESPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
        }
    }

    //7. gestion de respuesta del usuario respecto a la solicitud del permiso

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        tvResponse.setText(""+grantResults [0]);
        if (requestCode == REQUEST_CODE){
            if (grantResults [0] != PackageManager.PERMISSION_GRANTED){
                new AlertDialog.Builder(this)
                        .setTitle("Permission Denied")
                        .setMessage("Camera permission was denied. Please go to settings and enable it.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));

                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        }).create().show();
            }else{
                // Permission granted
                Toast.makeText(this, "Usted no ha otorgado los permisos", Toast.LENGTH_SHORT).show();
            }
        }else{
            // Handle other request codes
            Toast.makeText(this, "Usted no ha otorgado los permisos", Toast.LENGTH_SHORT).show();

        }

    }
    

    //2. enlace de objetos
    private void initObject () {

        btnCheckPermissions = findViewById(R.id.btnCheckPermission);
        btnRequestPermissions = findViewById(R.id.btnRequestPermission);

        tvCamera = findViewById(R.id.tvCamera);
        tvBiometric = findViewById(R.id.tvDactilar);
        tvExternalWs = findViewById(R.id.tvEws);
        tvReadExternalS = findViewById(R.id.tvRS);
        tvInternet = findViewById(R.id.tvInternet);
        tvResponse = findViewById(R.id.tvResponse);
        versionAndroid = findViewById(R.id.tvVersionAndroid);
        pbLevelBatt= findViewById(R.id.pbLevelBaterry);
        tvLevelBatt = findViewById(R.id.tvLevelBaterry);
        tvConexion = findViewById(R.id.tvConexion);
        btnOn = findViewById(R.id.btnOn);
        btnOff= findViewById(R.id.btnOff);

    }


    private class CameraManager {
    }

    private class ConnectivitiManager {
    }
