package com.example.canvaspre.model.database;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteTask {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    // Método para ejecutar una tarea en segundo plano y luego en el hilo principal
    public void asyncTask(Runnable backgroundTask, Runnable postExecuteTask) {
        executorService.execute(() -> {
            backgroundTask.run();
            mainHandler.post(postExecuteTask);
        });
    }

    // Método para ejecutar una tarea en segundo plano solamente
    public void asyncTask(Runnable backgroundTask) {
        executorService.execute(backgroundTask);
    }
}