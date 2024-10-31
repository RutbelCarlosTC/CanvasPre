package com.example.canvaspre.canvas;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.canvaspre.controller.EventViewModel;
import com.example.canvaspre.model.ent.DoorEntity;
import com.example.canvaspre.model.ent.PictureEntity;
import com.example.canvaspre.model.ent.RoomAndVertex;
import com.example.canvaspre.model.ent.RoomEntity;
import com.example.canvaspre.model.ent.VertexEntity;

import java.util.HashMap;
import java.util.List;

public class GalleryView extends View {
    private static final String TAG = "GalleryView";

    private float WIDTH;
    private float HEIGHT;

    private final float HORIZONTAL_DOOR = 0f;
    private final float VERTICAL_DOOR = 90f;

    private EventViewModel eventViewModel;
    private List<RoomAndVertex> roomsList; // Lista de habitaciones
    private HashMap<RoomAndVertex, HashMap<String, Float>> roomsParameters = new HashMap<>();

    private List<PictureEntity> pictureEntityList;
    private List<DoorEntity> doorEntityList;

    private Region region;
    private final Paint roomPaint;
    private final Paint doorPaint;
    private final Paint picturePaint;
    private final Paint textPaint;

    private HashMap<PictureEntity, Region> pictureRegionList;

    public GalleryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        String layoutHeight = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        String layoutWidth = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_width");

        float density = getResources().getDisplayMetrics().density;
        HEIGHT = density * Float.parseFloat(layoutHeight.replace("dip", ""));
        WIDTH = density * Float.parseFloat(layoutWidth.replace("dip", ""));

        Log.d(TAG, "Dimension: " + WIDTH + "x" + HEIGHT);

        region = new Region();
        pictureRegionList = new HashMap<>();

        // Configurar pinturas
        roomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        roomPaint.setStrokeWidth(10f);
        roomPaint.setStyle(Paint.Style.STROKE);
        roomPaint.setColor(Color.BLACK);

        picturePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        picturePaint.setColor(Color.parseColor("#ff9880"));
        picturePaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID));

        doorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        doorPaint.setColor(Color.parseColor("#ff9800"));
        doorPaint.setStrokeWidth(10f);
        doorPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32f);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (roomsList != null) {
            for (RoomAndVertex room : roomsList) {
                setRoom(room);
                if (roomsParameters.get(room) != null) {
                    drawRoom(canvas, room);

                }
            }
        }
    }

    private void setRoom(RoomAndVertex roomAndVertex) {
        HashMap<String, Float> params = calculateParameters(roomAndVertex);
        if (params != null) {
            float scale = params.get("SCALE");
            float offsetX = params.get("OFFSET_X");
            float offsetY = params.get("OFFSET_Y");
            float marginLeft = params.get("MARGIN_LEFT");
            float marginTop = params.get("MARGIN_TOP");

            for (VertexEntity vertex : roomAndVertex.vertexEntityList) {
                vertex.setX((scale * vertex.getX()) - offsetX + marginLeft);
                vertex.setY((scale * vertex.getY()) - offsetY + marginTop);
            }

            roomsParameters.put(roomAndVertex, params);
        }
    }

    private HashMap<String, Float> calculateParameters(RoomAndVertex room) {
        List<VertexEntity> vertexEntityList = room.vertexEntityList;
        if (vertexEntityList == null) return null;

        int leftX = (int) WIDTH, topY = (int) HEIGHT, rightX = 0, bottomY = 0;

        for (VertexEntity vertex : vertexEntityList) {
            int x = (int) vertex.getX();
            int y = (int) vertex.getY();
            if (x < leftX) leftX = x;
            if (x > rightX) rightX = x;
            if (y < topY) topY = y;
            if (y > bottomY) bottomY = y;
        }

        float scaleFactor = 0.86f * Math.min(WIDTH / (rightX - leftX), HEIGHT / (bottomY - topY));
        float marginLeft = (WIDTH - scaleFactor * (rightX - leftX)) / 2;
        float offsetX = leftX * scaleFactor;
        float offsetY = topY * scaleFactor;

        HashMap<String, Float> params = new HashMap<>();
        params.put("SCALE", scaleFactor);
        params.put("OFFSET_X", offsetX);
        params.put("OFFSET_Y", offsetY);
        params.put("MARGIN_LEFT", marginLeft);
        params.put("MARGIN_TOP", 80f);

        return params;
    }

    private void drawRoom(Canvas canvas, RoomAndVertex room) {
        Path path = new Path();
        List<VertexEntity> vertexEntityList = room.vertexEntityList;
        path.moveTo(vertexEntityList.get(0).getX(), vertexEntityList.get(0).getY());

        for (int i = 1; i < vertexEntityList.size(); i++) {
            path.lineTo(vertexEntityList.get(i).getX(), vertexEntityList.get(i).getY());
        }
        path.close();

        canvas.drawPath(path, roomPaint);
        float x = region.getBounds().centerX();
        float y = region.getBounds().centerY();
        canvas.drawText(room.roomEntity.getLabel(), x, y, textPaint);
    }

    private void drawDoors(Canvas canvas, List<DoorEntity> doors) {
        for (DoorEntity door : doors) {
            float x0, x1, y0, y1;
            if (door.getAngle() == HORIZONTAL_DOOR) {
                x0 = door.getX() - door.getWidth() / 2;
                x1 = door.getX() + door.getWidth() / 2;
                y0 = y1 = door.getY();
            } else {
                x0 = x1 = door.getX();
                y0 = door.getY() - door.getWidth() / 2;
                y1 = door.getY() + door.getWidth() / 2;
            }
            canvas.drawLine(x0, y0, x1, y1, doorPaint);
        }
    }

    private void drawPictures(Canvas canvas, List<PictureEntity> pictures) {
        for (PictureEntity picture : pictures) {
            Path path = new Path();
            path.addCircle(picture.x, picture.y, 68f, Path.Direction.CW);
            canvas.drawPath(path, picturePaint);

            Region pictureRegion = new Region();
            pictureRegion.setPath(path, new Region(0, 0, (int) WIDTH, (int) HEIGHT));
            pictureRegionList.put(picture, pictureRegion);

            canvas.drawText(String.valueOf(picture.pictureId), picture.x, picture.y, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (PictureEntity picture : pictureRegionList.keySet()) {
                if (pictureRegionList.get(picture).getBounds().contains((int) event.getX(), (int) event.getY())) {
                    Log.d(TAG, "Picture clicked: " + picture.pictureId);
                    eventViewModel.setPictureSelected(picture.pictureId);
                }
            }
        }
        return true;
    }

    public void setRooms(List<RoomAndVertex> roomsList) {
        this.roomsList = roomsList;
        for (RoomAndVertex room : roomsList) {
            setRoom(room);
        }
        invalidate();
    }

    public void setEventViewModel(EventViewModel eventViewModel) {
        this.eventViewModel = eventViewModel;
    }
}
