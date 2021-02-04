package com.shobpabo.kobekhola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;



class Weather extends AsyncTask<String,Void,String>{
    @Override
    protected String doInBackground(String... address) {





        try {
            URL url = new URL(address[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Establish connection with address
            connection.connect();

            //retrieve data from url
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            //Retrieve data and return it as String
            int data = isr.read();
            String content = "";
            char ch;
            while (data != -1){
                ch = (char) data;
                content = content + ch;
                data = isr.read();
            }
            Log.i("Content",content);
            return content;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}



public class Main2Activity extends AppCompatActivity {
public TextView day;
    public TextView week;
    public TextView elaka;
    public Button sat;
    public Button sun;
    public Button mon;
    public Button wed;
    public Button tue;
    public Button thurs;
    public Button fri;
    public Button search;
    public Spinner spin;
    public String weekday_name;
    public ImageView adv;

    @Override
    protected void onStart() {
        super.onStart();
        String url = "https://www.shobpabo.com/ad/ad.png";

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.ad)
                .error(R.drawable.ad)
                .into(adv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         day = findViewById(R.id.barTv);
        elaka = findViewById(R.id.elakaTv);

        sat = findViewById(R.id.shoniB);
        sun = findViewById(R.id.robiB);
        mon = findViewById(R.id.shomB);
        tue = findViewById(R.id.mongolB);
        wed = findViewById(R.id.budhB);
        thurs = findViewById(R.id.brihoB);
        fri = findViewById(R.id.shukroB);

        search = findViewById(R.id.searchbt);
        spin = findViewById(R.id.spin);
        adv = findViewById(R.id.ad);


        String url = "https://www.shobpabo.com/ad/ad.png";

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.ad)
                .error(R.drawable.ad)
                .into(adv);



        ImageView splogo = findViewById(R.id.shobpabologo);
        splogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://Shobpabo.com")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/shobpabobd/")));
                }
            }
        });



        TextView tempT = findViewById(R.id.tempTv);
        TextView weatherT = findViewById(R.id.weatherTv);

        weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());

       // String weekday_name = "Thursday" ;


        String content;
        Weather weather = new Weather();

        try {
            content =  weather.execute("https://openweathermap.org/data/2.5/weather?q=Dhaka,bd&appid=b6907d289e10d714a6e88b30761fae22").get();
            //First we will check data is retrieve successfully or not
            Log.i("contentData",content);


            JSONObject jsonObject = new JSONObject(content);
            String weaterData = jsonObject.getString("weather");
            String mainTemp = jsonObject.getString("main");

            Log.i("weatherData",weaterData);

            JSONArray array = new JSONArray(weaterData);

            String main = "";
            String description = "";
            String tempreture = "";

            for (int i=0; i<array.length(); i++ ){

                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");

            }

            JSONObject mainPart = new JSONObject(mainTemp);
            tempreture = mainPart.getString("temp");
            Log.i("Tempreture",tempreture);

            tempT.setText("Temperature: "+tempreture+"°C");



          Log.i("main",main);
          Log.i("description",description);
            weatherT.setText("Weather: " +description);

        } catch (Exception e) {
            e.printStackTrace();
        }






        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoni();
                week();
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                robi();
                week();

            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shom();
                week();
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mongol();
                week();
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budh();
                week();
            }
        });
        thurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brihospoti();
                week();
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shukro();
                week();
            }
        });







        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String area = spin.getSelectedItem().toString();
                String robi = "আগারগাঁ, তালতলা, শেরে বাংলা নগর, শেওড়াপাড়া, কাজী পাড়া, পল্লবী, মিরপুর-১০ , মিরপুর-১১ , মিরপুর-১২ মিরপুর-২, মিরপুর-১৩ , মিরপুর-১৪,মিরপুর-৬ মিরপুর-৭ ইব্রাহীমপুর, কচুখেত, কাফরুল, মহাখালী, নিউ ডিওএসএইচ, ওল্ড ডিওএসএইচ, কাকলী, তেজগাঁও ওল্ড এয়ারপোর্ট এরিয়া, তেজগাঁ ইন্ড্রাস্ট্রিয়াল এরিয়া, ক্যান্টনমেন্ট, গুলসান-১, ২, বনানী, মহাখালী কমার্শিয়াল এরিয়া, নাখালপাড়া, মহাখালী ইন্টার সিটি বাস টার্মিনাল এরিয়া, রামপুরা, বনশ্রী, খিলগাঁ, গোড়ান, মালিবাগের একাংশ, বাসাবো, ধলপুর, সায়েদাবাদ, মাদারটেক, মুগদা, কমলাপুরের একাংশ, যাত্রাবাড়ী একাংশ, শনির আখড়া, দনিয়া, রায়েরবাগ, সানারপাড় ";
                String mongol = "কাঁঠালবাগান, হাতিরপুল, মানিক মিয়া এভিনিউ, রাজাবাজার, মণিপুরিপাড়া, তেজকুনীপাড়া, ফার্মগেট, কাওয়ান বাজার, নীলক্ষেত, কাঁটাবন, এলিফ্যান্ট রোড, শুক্রাবাদ, সোবহানবাগ, ধানমন্ডি, হাজারীবাগ, জিগাতলা, রায়েরবাজার, পিলখানা, লালমাটিয়া ।";
                String budh = "বসুন্ধরা আবাসিক এলাকা, মধ্য ও উত্তর বাড্ডা, জগন্নাথপুর , বারিধারা , সাতারকুল, শাহাজাদপুর, নিকুঞ্জ ১, ২, কুড়িল, খিলখেত, উত্তরখান, দক্ষিণখান, জোয়ার সাহারা, আশকোনা, বিমানবন্দর সড়ক ও উত্তরা থেকে টঙ্গী সেতু ";
                String briho = "মোহাম্মাদপুর,মিরপুর-১ আদাবর, শ্যামলী, গাবতলী, মিরপুর স্টেডিয়াম , চিড়িয়াখানা, টেকনিক্যাল, কল্যাণপুর, আসাদগেট, ইস্কাটন, মগবাজার, বেইলি রোড, সিদ্ধেশ্বরী, মালিবাগের একাংশ, শাজাহানপুর, শান্তিনগর, শহীদবাগ, শান্তিবাগ, ফকিরেরপুল, পল্টন, মতিঝিল, টিকাটুলি, আরামবাগ, কাকরাইল, বিজয়নগর, সেগুনবাগিচা, হাইকোর্ট ভবন এলাকা, রমনা শিশু পার্ক, ঢাকা বিশ্ববিদ্যালয় এলাকা ।";
                String shukro = "বাংলাবাজার, পাটুয়াটুলী, ফরাশগঞ্জ, শ্যামবাজার, জুরাইন , করিমউল্লাহবাগ , পোস্তগোলা , শ্যামপুর , মীরহাজীরবাগ , দোলাইপাড় , টিপু সুলতান রোড, ধূপখোলা, গেণ্ডারিয়া, দয়াগঞ্জ, স্বামীবাগ, ধোলাইখাল, জয়কালী মন্দির, যাত্রাবাড়ীর দক্ষিন-পশ্চিম অংশ, ওয়ারী, আহসান মঞ্জিল, লালবাগ, কোতোয়ালী থানা, বংশাল, নবাবপুর, সদরঘাট, তাঁতীবাজার, লক্ষ্মীবাজার, শাঁখারী বাজার, চাঙ্খারপুল, গুলিস্থানের দক্ষিণ অংশ ।";

                    if (briho.contains(area)){
                    TextView week = findViewById(R.id.weekdayTv);
                    brihospoti();
                    week.setText("বৃহস্পতিবার পূর্ণ ও শুক্রবার অর্ধদিবস বন্ধ");

                }
                else if (budh.contains(area)){
                    TextView week = findViewById(R.id.weekdayTv);
                    budh();
                    week.setText("বুধবার পূর্ণ দিবস ও বৃহস্পতিবার অর্ধদিবস বন্ধ");

                }
                else if (mongol.contains(area)){
                    TextView week = findViewById(R.id.weekdayTv);
                    mongol();
                    week.setText("মঙ্গলবার পূর্ণ ও বুধবার  অর্ধদিবস বন্ধ");

                }

                else if (robi.contains(area)){
                    TextView week = findViewById(R.id.weekdayTv);
                    robi();
                    week.setText("রোববার পূর্ণ ও সোমবার অর্ধ দিবস বন্ধ");

                }
                else if (shukro.contains(area)){
                    TextView week = findViewById(R.id.weekdayTv);

                    week.setText("শুক্রবার পূর্ণ ও শনিবার অর্ধদিবস বন্ধ");
                    shukro();
                }
                else {
                    TextView week = findViewById(R.id.weekdayTv);

                    week.setText(area);
                }
            }
        });








        if (weekday_name.equals("Sunday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ রবিবার");
            robi();
        }
        if (weekday_name.equals("Friday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ শুক্রুবার");
            shukro();
        } if (weekday_name.equals("Saturday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ শনিবার");
            shoni();
        } if (weekday_name.equals("Monday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ সোমবার");
            shom();
        } if (weekday_name.equals("Tuesday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ মঙ্গলবার");
            mongol();
        }
        if (weekday_name.equals("Wednesday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ বুধবার");
            budh();
        }
        if (weekday_name.equals("Thursday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ বৃহস্পতিবার");
            brihospoti();
        }

    }
    void robi(){

        day.setText("রোববার পূর্ণ ও সোমবার অর্ধ দিবস বন্ধ");
        elaka.setText("এলাকার নাম: আগারগাঁ, তালতলা, শেরে বাংলা নগর, শ্যাওড়া পাড়া, কাজী পাড়া, পল্লবী, মিরপুর-১০, মিরপুর-১১, মিরপুর-১২, মিরপুর-১৩, মিরপুর-১৪, ইব্রাহীমপুর, কচুখেত, কাফরুল, মহাখালী, নিউ ডিওএসএইচ, ওল্ড ডিওএসএইচ, কাকলী, তেজগাঁও ওল্ড এয়ারপোর্ট এরিয়া, তেজগাঁ ইন্ড্রাস্ট্রিয়াল এরিয়া, ক্যান্টনমেন্ট, গুলসান-১, ২, বনানী, মহাখালী কমার্শিয়াল এরিয়া, নাখালপাড়া, মহাখালী ইন্টার সিটি বাস টার্মিনাল এরিয়া, রামপুরা, বনশ্রী, খিলগাঁ, গোড়ান, মালিবাগের একাংশ, বাসাবো, ধলপুর, সায়েদাবাদ, মাদারটেক, মুগদা, কমলাপুরের একাংশ, যাত্রাবাড়ী একাংশ, শনির আখড়া, দনিয়া, রায়েরবাগ, সানারপাড়।");
    }
    void shom(){

        day.setText("সোমবার অর্ধ দিবস বন্ধ");
        elaka.setText("এলাকার নাম: আগারগাঁ, তালতলা, শেরে বাংলা নগর, শ্যাওড়া পাড়া, কাজী পাড়া, পল্লবী, মিরপুর-১০, মিরপুর-১১, মিরপুর-১২, মিরপুর-১৩, মিরপুর-১৪, ইব্রাহীমপুর, কচুখেত, কাফরুল, মহাখালী, নিউ ডিওএসএইচ, ওল্ড ডিওএসএইচ, কাকলী, তেজগাঁও ওল্ড এয়ারপোর্ট এরিয়া, তেজগাঁ ইন্ড্রাস্ট্রিয়াল এরিয়া, ক্যান্টনমেন্ট, গুলসান-১, ২, বনানী, মহাখালী কমার্শিয়াল এরিয়া, নাখালপাড়া, মহাখালী ইন্টার সিটি বাস টার্মিনাল এরিয়া, রামপুরা, বনশ্রী, খিলগাঁ, গোড়ান, মালিবাগের একাংশ, বাসাবো, ধলপুর, সায়েদাবাদ, মাদারটেক, মুগদা, কমলাপুরের একাংশ, যাত্রাবাড়ী একাংশ, শনির আখড়া, দনিয়া, রায়েরবাগ, সানারপাড়।");
    }
    void mongol(){

        day.setText("মঙ্গলবার পূর্ণ ও বুধবার  অর্ধদিবস বন্ধ");
        elaka.setText("এলাকার নাম: কাঁঠালবাগান, হাতিরপুল, মানিক মিয়া এভিনিউ, রাজাবাজার, মণিপুরিপাড়া, তেজকুনীপাড়া, ফার্মগেট, কাওয়ান বাজার, নীলক্ষেত, কাঁটাবন, এলিফ্যান্ট রোড, শুক্রাবাদ, সোবহানবাগ, ধানমন্ডি, হাজারীবাগ, জিগাতলা, রায়েরবাজার, পিলখানা, লালমাটিয়া।");
    }
    void budh(){

        day.setText("বুধবার পূর্ণ দিবস ও বৃহস্পতিবার অর্ধদিবস বন্ধ");
        elaka.setText("এলাকার নাম: বসুন্ধরা আবাসিক এলাকা, মধ্য ও উত্তর বাড্ডা, জগন্নাথপুর, বারিধারা, সাতারকুল, শাহাজাদপুর, নিকুঞ্জ-১, ২, কুড়িল, খিলখেত, উত্তরখান, দক্ষিণখান, জোয়ার সাহারা, আশকোনা, বিমানবন্দর সড়ক ও উত্তরা থেকে টঙ্গী সেতু।");
    }
    void brihospoti(){

        day.setText("বৃহস্পতিবার পূর্ণ ও শুক্রবার অর্ধদিবস বন্ধ");
        elaka.setText("এলাকার নাম: মোহাম্মাদপুর, আদাবর, শ্যামলী, গাবতলী, মিরপুর স্টেডিয়াম, চিড়িয়াখানা, টেকনিক্যাল, কল্যাণপুর, আসাদগেট, ইস্কাটন, মগবাজার, বেইলি রোড, সিদ্ধেশ্বরী, মালিবাগের একাংশ, শাজাহানপুর, শান্তিনগর, শহীদবাগ, শান্তিবাগ, ফকিরেরপুল, পল্টন, মতিঝিল, টিকাটুলি, আরামবাগ, কাকরাইল, বিজয়নগর, সেগুনবাগিচা, হাইকোর্ট ভবন এলাকা, রমনা শিশু পার্ক, ঢাকা বিশ্ববিদ্যালয় এলাকা।");
    }
    void shukro(){

        day.setText("শুক্রবার পূর্ণ ও শনিবার অর্ধদিবস বন্ধ");
        elaka.setText("এলাকার নাম: বাংলাবাজার, পাটুয়াটুলী, ফরাশগঞ্জ, শ্যামবাজার, জুরাইন, করিমউল্লাহবাগ, পোস্তগোলা, শ্যামপুর, মীরহাজীরবাগ, দোলাইপাড়, টিপু সুলতান রোড, ধূপখোলা, গেণ্ডারিয়া, দয়াগঞ্জ, স্বামীবাগ, ধোলাইখাল, জয়কালী মন্দির, যাত্রাবাড়ীর দক্ষিন-পশ্চিম অংশ, ওয়ারী, আহসান মঞ্জিল, লালবাগ, কোতোয়ালী থানা, বংশাল, নবাবপুর, সদরঘাট, তাঁতীবাজার, লক্ষ্মীবাজার, শাঁখারী বাজার, চাঙ্খারপুল, গুলিস্থানের দক্ষিণ অংশ।");
    }
    void shoni(){

        day.setText("শনিবার অর্ধদিবস বন্ধ");
        elaka.setText("এলাকার নাম: বাংলাবাজার, পাটুয়াটুলী, ফরাশগঞ্জ, শ্যামবাজার, জুরাইন, করিমউল্লাহবাগ, পোস্তগোলা, শ্যামপুর, মীরহাজীরবাগ, দোলাইপাড়, টিপু সুলতান রোড, ধূপখোলা, গেণ্ডারিয়া, দয়াগঞ্জ, স্বামীবাগ, ধোলাইখাল, জয়কালী মন্দির, যাত্রাবাড়ীর দক্ষিন-পশ্চিম অংশ, ওয়ারী, আহসান মঞ্জিল, লালবাগ, কোতোয়ালী থানা, বংশাল, নবাবপুর, সদরঘাট, তাঁতীবাজার, লক্ষ্মীবাজার, শাঁখারী বাজার, চাঙ্খারপুল, গুলিস্থানের দক্ষিণ অংশ।");
    }
    void week(){
        if (weekday_name.equals("Sunday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ রবিবার");

        }
        if (weekday_name.equals("Friday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ শুক্রুবার");

        } if (weekday_name.equals("Saturday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ শনিবার");

        } if (weekday_name.equals("Monday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ সোমবার");

        } if (weekday_name.equals("Tuesday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ মঙ্গলবার");

        }
        if (weekday_name.equals("Wednesday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ বুধবার");

        }
        if (weekday_name.equals("Thursday")) {

            TextView week = findViewById(R.id.weekdayTv);

            week.setText("আজ বৃহস্পতিবার");

        }
    }

}
