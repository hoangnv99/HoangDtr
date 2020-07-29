package vn.edu.hoangnguyen.thi59123_1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class SecondFragment extends Fragment implements View.OnClickListener {
    EditText edtmonhoc, edtf, edtl, edtgd;
    ImageView imgf, imgl;
    Button btnadd, btnxem;
    Spinner spinner;

    NavController navController;
    String[] dsthi;
    ArrayList<String> MangDL = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity)getActivity()).navController = navController;
        MangDL = ((MainActivity) getActivity()).Mangmain;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtmonhoc = view.findViewById(R.id.edtmonhoc);
        edtf = view.findViewById(R.id.edtf);
        edtl = view.findViewById(R.id.edtl);
        edtgd = view.findViewById(R.id.edtgd);
        imgf = view.findViewById(R.id.imgf);
        imgl = view.findViewById(R.id.imgl);
        btnadd = view.findViewById(R.id.btnadd);
        btnxem = view.findViewById(R.id.btnxem);
        btnadd.setOnClickListener(this);
        btnxem.setOnClickListener(this);
        imgl.setOnClickListener(this);
        imgf.setOnClickListener(this);
        spinner = view.findViewById(R.id.spinner);
        dsthi = ((MainActivity) getActivity()).getResources().getStringArray(R.array.dsthi);
        spinner.setAdapter(new ArrayAdapter<>(((MainActivity) getActivity()), android.R.layout.simple_list_item_1, dsthi));
        navController = NavHostFragment.findNavController(SecondFragment.this);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnadd:
                Them();
                break;
            case R.id.btnxem:
                Xem();
                break;
            case R.id.imgf:
                chonngayf();
                break;
            case R.id.imgl:
                chonngayl();
                break;

        }
    }

    private void chonngayl() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener langnghesukienclickvaongay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(i)
                        .append("/")
                        .append(i1)
                        .append("/")
                        .append(i2);
                edtl.setText(stringBuilder.toString());
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                langnghesukienclickvaongay,
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void chonngayf() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener langnghesukienclickvaongay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(i)
                        .append("/")
                        .append(i1)
                        .append("/")
                        .append(i2);
                edtf.setText(stringBuilder.toString());
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                langnghesukienclickvaongay,
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void Xem() {
        navController.navigate(R.id.action_SecondFragment_to_FirstFragment);

    }


    private void Them() {
        String ngayf = edtf.getText().toString();
        String ngayl = edtl.getText().toString();


        String mon = edtmonhoc.getText().toString();
        String diadiem = edtgd.getText().toString();

        String dsthi = spinner.getSelectedItem().toString();
        if (ngayf.length() > 0 && mon.length() > 0 && ngayl.length() > 0 && diadiem.length() > 0 && dsthi.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" ")
                    .append(mon)
                    .append("\n Môn học: ")
                    .append(ngayf)
                    .append("\n Ngày kết thúc : ")
                    .append(ngayl)
                    .append("\n Địa điểm: ")
                    .append(diadiem)
                    .append("\n Khóa học: ")
                    .append(dsthi);


            MangDL.add(stringBuilder.toString());
            Toast.makeText(getActivity(), " Thêm Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), " Thiếu thông tin! Mời nhập lại", Toast.LENGTH_SHORT).show();
        }

    }


}