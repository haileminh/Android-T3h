package com.hailm.duoihinhbatchugame;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PlayActivity extends Activity implements View.OnClickListener {
    public static final int SIZE = 16;
    private static final String HOVER = "Hover";
    private static final String GREY = "Grey";
    private Button btnHeart;
    private Button btnCoin;
    private Button btnNext;
    private int icoin;
    private int iheart;
    private String result;
    private Random rd;

    private List<Button> listBtnGreys;
    private List<Button> listBtnHovers;

    private List<Integer> listIdImages;
    private int indexCurrentImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        registerListenner();
        createCharacter();
    }


    private void initializeComponents() {
        result = null;
        indexCurrentImage = 0;
        rd = new Random();

        btnHeart = findViewById(R.id.btn_heart);
        btnCoin = findViewById(R.id.btn_coin);
        btnNext = findViewById(R.id.btn_next);

        icoin = Integer.parseInt(btnCoin.getText().toString());
        iheart = Integer.parseInt(btnHeart.getText().toString());
        listBtnGreys = new ArrayList<>();
        listBtnHovers = new ArrayList<>();
        addButtonsByID();
        listIdImages = new ArrayList<>();
        addIdImages();

        createQuestion();
    }


    private void registerListenner() {
        for (int i = 0; i < SIZE; i++) {
            listBtnHovers.get(i).setOnClickListener(this);
            listBtnHovers.get(i).setVisibility(View.VISIBLE);

            listBtnGreys.get(i).setOnClickListener(this);
            listBtnGreys.get(i).setVisibility(View.INVISIBLE);
        }
        btnNext.setOnClickListener(this);
    }

    private void createQuestion() {
        btnNext.setVisibility(View.INVISIBLE);
        String name = getResources().getResourceName(listIdImages.get(indexCurrentImage));
        int index = name.lastIndexOf("/");
        result = name.substring(index + 1, name.length());
        int drawableResourceId = this.getResources().getIdentifier(result + ".jpg", "drawable", this.getPackageName());
        ((ImageView) findViewById(R.id.iv_picture)).setImageResource(listIdImages.get(indexCurrentImage));
    }

    private void createCharacter() {
        List<Character> characterList = new ArrayList<>();

        for (int i = 0; i < result.length(); i++) {
            listBtnGreys.get(i).setVisibility(View.VISIBLE);
            characterList.add(result.charAt(i));
        }
        int number = 16 - result.length();
        for (int i = 0; i < number; i++) {
            characterList.add((char) (rd.nextInt(26) + 97));
        }
        Collections.shuffle(characterList);
        for (int i = 0; i < listBtnHovers.size(); i++) {
            listBtnHovers.get(i).setText(characterList.get(i) + "");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_grey_0:
                clickButton(listBtnGreys.get(0), GREY);
                break;
            case R.id.btn_grey_1:
                clickButton(listBtnGreys.get(1), GREY);
                break;
            case R.id.btn_grey_2:
                clickButton(listBtnGreys.get(2), GREY);
                break;
            case R.id.btn_grey_3:
                clickButton(listBtnGreys.get(3), GREY);
                break;
            case R.id.btn_grey_4:
                clickButton(listBtnGreys.get(4), GREY);
                break;
            case R.id.btn_grey_5:
                clickButton(listBtnGreys.get(5), GREY);
                break;
            case R.id.btn_grey_6:
                clickButton(listBtnGreys.get(6), GREY);
                break;
            case R.id.btn_grey_7:
                clickButton(listBtnGreys.get(7), GREY);
                break;
            case R.id.btn_grey_8:
                clickButton(listBtnGreys.get(8), GREY);
                break;
            case R.id.btn_grey_9:
                clickButton(listBtnGreys.get(9), GREY);
                break;
            case R.id.btn_grey_10:
                clickButton(listBtnGreys.get(10), GREY);
                break;
            case R.id.btn_grey_11:
                clickButton(listBtnGreys.get(11), GREY);
                break;
            case R.id.btn_grey_12:
                clickButton(listBtnGreys.get(12), GREY);
                break;
            case R.id.btn_grey_13:
                clickButton(listBtnGreys.get(13), GREY);
                break;
            case R.id.btn_grey_14:
                clickButton(listBtnGreys.get(14), GREY);
                break;
            case R.id.btn_grey_15:
                clickButton(listBtnGreys.get(15), GREY);
                break;
            case R.id.btn_hover_0:
                clickButton(listBtnHovers.get(0), HOVER);
                break;
            case R.id.btn_hover_1:
                clickButton(listBtnHovers.get(1), HOVER);
                break;
            case R.id.btn_hover_2:
                clickButton(listBtnHovers.get(2), HOVER);
                break;
            case R.id.btn_hover_3:
                clickButton(listBtnHovers.get(3), HOVER);
                break;
            case R.id.btn_hover_4:
                clickButton(listBtnHovers.get(4), HOVER);
                break;
            case R.id.btn_hover_5:
                clickButton(listBtnHovers.get(5), HOVER);
                break;
            case R.id.btn_hover_6:
                clickButton(listBtnHovers.get(6), HOVER);
                break;
            case R.id.btn_hover_7:
                clickButton(listBtnHovers.get(7), HOVER);
                break;
            case R.id.btn_hover_8:
                clickButton(listBtnHovers.get(8), HOVER);
                break;
            case R.id.btn_hover_9:
                clickButton(listBtnHovers.get(9), HOVER);
                break;
            case R.id.btn_hover_10:
                clickButton(listBtnHovers.get(10), HOVER);
                break;
            case R.id.btn_hover_11:
                clickButton(listBtnHovers.get(11), HOVER);
                break;
            case R.id.btn_hover_12:
                clickButton(listBtnHovers.get(12), HOVER);
                break;
            case R.id.btn_hover_13:
                clickButton(listBtnHovers.get(13), HOVER);
                break;
            case R.id.btn_hover_14:
                clickButton(listBtnHovers.get(14), HOVER);
                break;
            case R.id.btn_hover_15:
                clickButton(listBtnHovers.get(15), HOVER);
                break;
            case R.id.btn_next:
                continuousGame();
            default:
                break;
        }
    }


    private void clickButton(Button button, String type) {
        btnNext.setVisibility(View.INVISIBLE);
        if (iheart <= 0) {
            Toast.makeText(this, "YOU LOSE", Toast.LENGTH_SHORT).show();
            return;
        }

        changeColorGreyButtons(R.drawable.ic_tile_grey);

        if (button.getText().toString().equals("")) {
            return;
        }

        switch (type) {
            case HOVER:
                String textButton = button.getText().toString();
                for (int i = 0; i < result.length(); i++) {
                    String textGreyButton = listBtnGreys.get(i).getText().toString();
                    if (textGreyButton.isEmpty()) {
                        listBtnGreys.get(i).setText(textButton);
                        button.setVisibility(View.INVISIBLE);
                        if (checkFullGreyButtons()) {
                            checkAnswer();
                        }
                        break;
                    }
                }
                break;
            case GREY:
                String text1 = button.getText().toString();
                for (int i = 0; i < SIZE; i++) {
                    String text = listBtnHovers.get(i).getText().toString();
                    if (text.equals(text1) && !listBtnHovers.get(i).isShown()) {
                        listBtnHovers.get(i).setVisibility(View.VISIBLE);
                        listBtnHovers.get(i).setText(button.getText().toString());
                        button.setText("");
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    private void checkAnswer() {
        String anwser = "";
        for (int i = 0; i < listBtnGreys.size(); i++) {
            anwser += listBtnGreys.get(i).getText().toString();
        }

        if (anwser.equals(result)) {
            changeColorGreyButtons(R.drawable.ic_tile_true);

            if (indexCurrentImage == listIdImages.size() - 1) {
                Toast.makeText(this, "YOU WIN", Toast.LENGTH_LONG).show();
                return;
            }
            btnNext.setVisibility(View.VISIBLE);
            return;
        }

        btnNext.setVisibility(View.INVISIBLE);
        iheart--;
        btnHeart.setText(iheart + "");
        changeColorGreyButtons(R.drawable.ic_tile_false);
    }

    private boolean checkFullGreyButtons() {
        for (int i = 0; i < result.length(); i++) {
            if (listBtnGreys.get(i).getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    private void continuousGame() {
        icoin += 100;
        btnCoin.setText(icoin + "");
        result = "";

        for (int i = 0; i < SIZE; i++) {
            listBtnGreys.get(i).setText("");
            listBtnGreys.get(i).setVisibility(View.INVISIBLE);
            listBtnHovers.get(i).setVisibility(View.VISIBLE);
            listBtnHovers.get(i).setText("");
        }

        changeColorGreyButtons(R.drawable.ic_tile_grey);
        indexCurrentImage++;
        createQuestion();
        createCharacter();
    }

    private void changeColorGreyButtons(int id) {
        for (int i = 0; i < listBtnGreys.size(); i++) {
            listBtnGreys.get(i).setBackgroundResource(id);
        }
    }

    private void addIdImages() {
        listIdImages.add(R.drawable.aomua);
        listIdImages.add(R.drawable.hoidong);
        listIdImages.add(R.drawable.hongtam);
        listIdImages.add(R.drawable.khoailang);
        listIdImages.add(R.drawable.kiemchuyen);
        listIdImages.add(R.drawable.lancan);
        listIdImages.add(R.drawable.masat);
        listIdImages.add(R.drawable.nambancau);
        listIdImages.add(R.drawable.oto);
        listIdImages.add(R.drawable.quyhang);
        listIdImages.add(R.drawable.songsong);
        listIdImages.add(R.drawable.thattinh);
        listIdImages.add(R.drawable.thothe);
        listIdImages.add(R.drawable.tichphan);
        listIdImages.add(R.drawable.tohoai);
        listIdImages.add(R.drawable.totien);
        listIdImages.add(R.drawable.tranhthu);
        listIdImages.add(R.drawable.vuaphaluoi);
        listIdImages.add(R.drawable.vuonbachthu);
        listIdImages.add(R.drawable.xakep);
        listIdImages.add(R.drawable.xaphong);
        listIdImages.add(R.drawable.xedapdien);
        listIdImages.add(R.drawable.baocao);
        listIdImages.add(R.drawable.canthiep);
        listIdImages.add(R.drawable.cattuong);
        listIdImages.add(R.drawable.chieutre);
        listIdImages.add(R.drawable.danhlua);
        listIdImages.add(R.drawable.danong);
        listIdImages.add(R.drawable.giandiep);
        listIdImages.add(R.drawable.giangmai);
        Collections.shuffle(listIdImages);
    }

    private void addButtonsByID() {
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_0));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_1));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_2));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_3));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_4));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_5));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_6));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_7));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_8));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_9));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_10));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_11));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_12));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_13));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_14));
        listBtnGreys.add((Button) findViewById(R.id.btn_grey_15));

        listBtnHovers.add((Button) findViewById(R.id.btn_hover_0));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_1));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_2));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_3));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_4));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_5));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_6));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_7));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_8));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_9));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_10));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_11));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_12));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_13));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_14));
        listBtnHovers.add((Button) findViewById(R.id.btn_hover_15));
    }


}
