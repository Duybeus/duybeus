package com.example.QuanLyMuonTraSachThuVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.QuanLyMuonTraSachThuVien.DAO.PhieuMuonDAO;
import com.example.QuanLyMuonTraSachThuVien.DAO.ThuThuDAO;

import com.example.QuanLyMuonTraSachThuVien.DoiMk.DoiMKFragment;
import com.example.QuanLyMuonTraSachThuVien.LoaiSach.LoaiSachFragment;
import com.example.QuanLyMuonTraSachThuVien.PhieuMuon.PhieuMuonFragment;
import com.example.QuanLyMuonTraSachThuVien.Sach.SachFragment;
import com.example.QuanLyMuonTraSachThuVien.ThanhVien.ThanhVienFragment;

import com.example.QuanLyMuonTraSachThuVien.themUser.ThemUserFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView tvUser;
    PhieuMuonDAO dao;
    ThuThuDAO thuThuDAO;
    private int currentFragment=FRAGMENT_PHIEUMUON;

    private  static final int FRAGMENT_PHIEUMUON =0;
    private  static final int FRAGMENT_SACH =1;
    private  static final int FRAGMENT_LOAISACH =2;
    private  static final int FRAGMENT_THANHVIEN =3;
    private  static final int FRAGMENT_TOP10 =4;
    private  static final int FRAGMENT_DOANHTHU =5;
    private  static final int FRAGMENT_THEMNGUOIDUNG =6;
    private  static final int FRAGMENT_DOIMK=7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,
                toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
       NavigationView navigationView = findViewById(R.id.navigationView);
         navigationView.setItemIconTintList(null);
        manager.beginTransaction()
                .replace(R.id.content_fame, phieuMuonFragment)
                .commit();



        //show user header
        headerView = navigationView.getHeaderView(0);
        tvUser = headerView.findViewById(R.id.tvUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
//        thuThuDAO = new ThuThuDAO(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String username = thuThu.getHoTen();
        tvUser.setText("Welcome"+ "!");
        //admin co quyen add user
        if (user != null && user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.sub_ThemUser).setVisible(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.navPhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, phieuMuonFragment)
                                .commit();
                        break;
                    case R.id.navLoaiSach:
                        setTitle("Quản lý  Sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, loaiSachFragment)
                                .commit();
                        break;
                    case R.id.navSach:
                        setTitle("Quản lý Loại sách");

                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, sachFragment)
                                .commit();
                        break;
                    case R.id.navThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, thanhVienFragment)
                                .commit();

                        break;
                    case R.id.sub_ThemUser:
                        setTitle("Quản lý thêm người dùng");
                        ThemUserFragment themNguoiDungFragment = new ThemUserFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, themNguoiDungFragment)
                                .commit();
                        break;
                    case R.id.sub_DoiMK:
                        setTitle("Đổi mật khẩu");
                        DoiMKFragment changePassFragment = new DoiMKFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_DangXuat:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;


                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fame,fragment);
        transaction.commit();
    }
}