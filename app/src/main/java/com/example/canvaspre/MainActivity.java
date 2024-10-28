package com.example.canvaspre;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider;


import com.example.canvaspre.canvas.RoomView;
import com.example.canvaspre.controller.EventViewModel;
import com.example.canvaspre.fragments.GalleryFragment;
import com.example.canvaspre.fragments.HomeFragment;
import com.example.canvaspre.fragments.PictureFragment;
import com.example.canvaspre.fragments.RoomFragment;
import com.example.canvaspre.fragments.RoomInfoDialogFragment;
import com.example.canvaspre.model.database.FileRepository;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;
    private FileRepository fileRepository;
    private FragmentManager fragmentManager;
    private RoomFragment roomFragment;
    private HomeFragment homeFragment;
    private GalleryFragment galleryFragment;
    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(onItemSelectedListener);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.onRoomSelected().observe( this, roomSelectedObserver);
        eventViewModel.onPictureSelected().observe ( this, pictureSelectedObserver);
        eventViewModel.onCloseFragment().observe( this, closeFragmentObserver);

        fragmentManager = getSupportFragmentManager();

        init();
    }

    private void init() {
        loadFragment(HomeFragment.newInstance());
    }

    private final Observer<Integer> roomSelectedObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer roomId) {
            Log.d("TAG", "roomId:" + roomId);
            roomFragment = RoomFragment.newInstance(roomId);
            loadFragment(roomFragment);
        }
    };

    private final Observer<Integer> pictureSelectedObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer pictureId) {
            Log.d("TAG", "pictureld:" + pictureId);
            loadFragment(PictureFragment.newInstance(pictureId));
        }
    };

    private final Observer<Integer> closeFragmentObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer roomId) {
            Log.d( "TAG", "roomId:" + roomId);
            loadFragment(RoomFragment.newInstance(roomId));
        }
    };
    private final NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_home) {
                homeFragment = HomeFragment.newInstance();
                loadFragment(homeFragment);
                return true;
            } else if (item.getItemId() == R.id.menu_map_room) {
                galleryFragment = GalleryFragment.newInstance("", "");
                loadFragment(galleryFragment);
                return true;
            } else {
                return false;
            }
        }
    };


    private void loadFragment(Fragment fragment) {
        if(fragmentManager != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }

    //@Override
    public void closePictureFragment() {
        Log.d(TAG, "MainActivity: close picture fragment");
        if (galleryFragment == null) {
            galleryFragment = (GalleryFragment) getSupportFragmentManager().findFragmentByTag("GALLERY_FRAGMENT");
        }

        if (galleryFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(galleryFragment)
                    .commit();
            galleryFragment = null;
            Log.d(TAG, "Gallery fragment closed");
        } else {
            Log.d(TAG, "Gallery fragment not found");
        }
    }


}

