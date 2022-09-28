package CaculationProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compare {

    public void start(String answers_path, String unchecked_path) {

        try {
            //调用写好的方法，用List有序集合存储题目文件的答案和答案文件的答案
            List<String> answers = answerReader(answers_path);
            List<String> unchecked = uncheckedReader(unchecked_path);
            //定义正确的题号集合和错误的题号集合
            List<String> correct = new ArrayList<>();
            List<String> wrong = new ArrayList<>();
            //考虑两个文件题目数量不对等的情况：
            // 取题目文件的答案个数和答案文件的答案个数中的较小值
            int min = Math.min(unchecked.size(), answers.size());
            int num = 1;
            for (int i = 0; i < min; i++) {
                //逐个比较，判断答案是否相等
                if (answers.get(i).equals(unchecked.get(i))) {
                    correct.add(String.valueOf(num++));
                } else {
                    wrong.add(String.valueOf(num++));
                }
            }

            File grade = new File("Grade.txt");
            if (grade.exists()) {
                grade.delete();
            }
            if (grade.createNewFile()) {
                FileOutputStream gradeOutput = new FileOutputStream(grade);
                PrintStream gradePrintStream = new PrintStream(gradeOutput);
                String corrects = String.join(",", correct);
                gradePrintStream.println("Correct：" + correct.size() +
                        " (" + corrects + ")");
                String wrongs = String.join(",", wrong);
                gradePrintStream.println("Wrong：" + wrong.size() +
                        " (" + wrongs + ")");
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("文件读入异常");
        }
    }

    //读取标准答案
    public static List<String> answerReader(String path) throws IOException {
        BufferedReader answerReader = new BufferedReader(new FileReader(path));
        String answer = "";
        List<String> answers = new ArrayList<>();
        while ((answer = answerReader.readLine()) != null) {
            String[] split = answer.split("=");
            if (split.length >= 2) {
                answers.add(split[1]);
            } else {
                answers.add(" ");
            }
        }
        return answers;
    }

    public static List<String> uncheckedReader(String path) throws IOException {
        BufferedReader uncheckedReader = new BufferedReader(new FileReader(path));
        String answer = "";
        List<String> answers = new ArrayList<>();
        while ((answer = answerReader.readLine()) != null) {
            String[] split = answer.split(" ");
            //有题号，所以选择split[1]，并添加到答案集合中
            answers.add(split[1]);
        }
        return answers;
    }
}
