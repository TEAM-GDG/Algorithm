import java.io.*;

public class 피보나치함수_1003 {

    static int[][] f =new int[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        f[0][0]=1;
        f[0][1]=0;
        f[1][0]=0;
        f[1][1]=1;

        for(int i=2;i<41;i++) {
            f[i][0]=f[i-1][0]+f[i-2][0];
            f[i][1]=f[i-1][1]+f[i-2][1];
        }

        for(int i=0;i<T;i++) {
            int t=Integer.parseInt(br.readLine());
            System.out.println(f[t][0]+" "+f[t][1]);
        }
    }

}
