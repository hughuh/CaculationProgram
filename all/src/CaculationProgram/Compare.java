package CaculationProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compare {

    public void start(String answers_path, String unchecked_path) {

        try {
            //����д�õķ�������List���򼯺ϴ洢��Ŀ�ļ��Ĵ𰸺ʹ��ļ��Ĵ�
            List<String> answers = answerReader(answers_path);
            List<String> unchecked = uncheckedReader(unchecked_path);
            //������ȷ����ż��Ϻʹ������ż���
            List<String> correct = new ArrayList<>();
            List<String> wrong = new ArrayList<>();
            //���������ļ���Ŀ�������Եȵ������
            // ȡ��Ŀ�ļ��Ĵ𰸸����ʹ��ļ��Ĵ𰸸����еĽ�Сֵ
            int min = Math.min(unchecked.size(), answers.size());
            int num = 1;
            for (int i = 0; i < min; i++) {
                //����Ƚϣ��жϴ��Ƿ����
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
                gradePrintStream.println("Correct��" + correct.size() +
                        " (" + corrects + ")");
                String wrongs = String.join(",", wrong);
                gradePrintStream.println("Wrong��" + wrong.size() +
                        " (" + wrongs + ")");
            }
        } catch (FileNotFoundException e) {
            System.out.println("�ļ�������");
        } catch (IOException e) {
            System.out.println("�ļ������쳣");
        }
    }

    //��ȡ��׼��
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
            //����ţ�����ѡ��split[1]������ӵ��𰸼�����
            answers.add(split[1]);
        }
        return answers;
    }
}
