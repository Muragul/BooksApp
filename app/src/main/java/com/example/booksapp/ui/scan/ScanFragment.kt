package com.example.booksapp.ui.scan

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import androidx.fragment.app.Fragment
import com.example.booksapp.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.fragment_scan.*
import java.lang.Exception

class ScanFragment : Fragment(R.layout.fragment_scan) {
    private val requestCodeCameraPermission = 1001
    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ContextCompat.checkSelfPermission(
                context as Activity, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission()
        } else {
            setupControls()
        }
    }

    private fun setupControls() {
        detector = BarcodeDetector.Builder(context as Activity).build()
        cameraSource = CameraSource.Builder(context as Activity, detector)
            .setAutoFocusEnabled(true)
            .build()
        camera_surface_view.holder.addCallback(surfaceCallBack)
        detector.setProcessor(processor)
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setupControls()
            else
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val surfaceCallBack = object : SurfaceHolder.Callback {
        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        }

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            cameraSource.stop()
        }

        override fun surfaceCreated(p0: SurfaceHolder) {
            try {
                if (ActivityCompat.checkSelfPermission(
                        context as Activity,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                cameraSource.start(p0)
            } catch (e: Exception) {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val processor = object : Detector.Processor<Barcode> {
        override fun release() {
        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            if (detections.detectedItems.isNotEmpty()) {
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("book_id", code.displayValue.toInt())
                startActivity(intent)
            } else {
                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
            }
        }

    }
}