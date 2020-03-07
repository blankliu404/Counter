package com.blank.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.TextView;

public class PingpongCourtCounterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingpong_court_counter);
        alert("乒乓球比赛计分器使用说明", "1、点按1分按钮可对相应队伍进行加分\n" +
                "2、有小分获胜方判断机制，标准参照11球制\n" +
                "3、小分获胜方的大分更新且弹出提示\n" +
                "4、点按重置按钮重置比分\n" +
                "5、点按结算按钮结算比赛结果");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void backHome(View view) {   //返回主页
        this.finish();
        onBackPressed();
    }

    public void teamA_AddScore(View view) {
        /*
         * TeamA加分  大分被动更新
         * */
        TextView teamA_SmallScore = findViewById(R.id.teamA_SmallScore);
        int teamA_smallScore = Integer.parseInt(teamA_SmallScore.getText().toString());

        TextView teamB_SmallScore = findViewById(R.id.teamB_SmallScore);
        int teamB_smallScore = Integer.parseInt(teamB_SmallScore.getText().toString());
        teamA_smallScore++;
        if (judgeWiner(teamA_smallScore, teamB_smallScore)) {
            //team A 赢下本局，大分加一,小分全部置零，弹出本局team A 本局获胜信息
            TextView teamA_BigScore = findViewById(R.id.teamA_BigScore);
            int teamA_bigScore = Integer.parseInt(teamA_BigScore.getText().toString());
            teamA_bigScore++;
            teamA_BigScore.setText("" + teamA_bigScore);

            teamA_SmallScore.setText("0");
            teamB_SmallScore.setText("0");

            alert("本局Team A获胜，大分加一！");
        } else {
            teamA_SmallScore.setText("" + teamA_smallScore);
        }
    }

    public void teamB_AddScore(View view) {
        TextView teamA_SmallScore = findViewById(R.id.teamA_SmallScore);
        int teamA_smallScore = Integer.parseInt(teamA_SmallScore.getText().toString());

        TextView teamB_SmallScore = findViewById(R.id.teamB_SmallScore);
        int teamB_smallScore = Integer.parseInt(teamB_SmallScore.getText().toString());
        teamB_smallScore++;
        if (judgeWiner(teamB_smallScore, teamA_smallScore)) {
            //team B 赢下本局，大分加一,小分全部置零，弹出本局team B 本局获胜信息
            TextView teamB_BigScore = findViewById(R.id.teamB_BigScore);
            int teamB_bigScore = Integer.parseInt(teamB_BigScore.getText().toString());
            teamB_bigScore++;
            teamB_BigScore.setText("" + teamB_bigScore);

            teamA_SmallScore.setText("0");
            teamB_SmallScore.setText("0");

            alert("本局Team B获胜，大分加一！");
        } else {
            teamB_SmallScore.setText("" + teamB_smallScore);
        }
    }

    private boolean judgeWiner(int score1, int score2) {
        /*
         * 判断单局胜负和分数限定
         * */
        if (Math.abs(score1 - score2) <= 1) {
            return false;   //没赢下本局
        } else {
            if (score1 < 11) return false;  //没赢下本局
            else return true;   //前者赢下本局
        }

    }

    private void alert(String message) {
        /*
         * 提示信息
         * */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.show();
    }

    private void alert(String title, String message) {
        /*
         * 提示信息
         * */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void reset(View view) {
        /*
         * 重置比分
         * */
        TextView teamA_BigScore = findViewById(R.id.teamA_BigScore);
        TextView teamB_BigScore = findViewById(R.id.teamB_BigScore);
        TextView teamA_SmallScore = findViewById(R.id.teamA_SmallScore);
        TextView teamB_SmallScore = findViewById(R.id.teamB_SmallScore);

        teamA_BigScore.setText("0");
        teamB_BigScore.setText("0");
        teamA_SmallScore.setText("0");
        teamB_SmallScore.setText("0");
        /*
         * 发送提示信息
         * */
        alert("重置成功!");
    }

    public void over(View view) {
        /*
         * 结算比赛得分
         * */
        TextView teamA_BigScore = findViewById(R.id.teamA_BigScore);
        TextView teamB_BigScore = findViewById(R.id.teamB_BigScore);

        int teamA_bigScore = Integer.parseInt(teamA_BigScore.getText().toString());
        int teamB_bigScore = Integer.parseInt(teamB_BigScore.getText().toString());

        if(teamA_bigScore > teamB_bigScore)alert("比赛结果","Team A取得了最后胜利！");
        else if (teamA_bigScore < teamB_bigScore)alert("比赛结果","Team B取得了最后胜利！");
        else {      //大分相等，比较小分
            TextView teamA_SmallScore = findViewById(R.id.teamA_SmallScore);
            TextView teamB_SmallScore = findViewById(R.id.teamB_SmallScore);

            int teamA_smallScore = Integer.parseInt(teamA_SmallScore.getText().toString());
            int teamB_smallScore = Integer.parseInt(teamB_SmallScore.getText().toString());

            if(teamA_smallScore == teamB_smallScore) alert("比赛结果","难分伯仲！");
            else if (teamA_smallScore > teamB_smallScore) alert("比赛结果","Team A取得了最后胜利！");
            else alert("比赛结果","Team B取得了最后胜利！");;
        }
    }
}
