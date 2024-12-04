package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

/**
 * 파일의 크기가 크지 않아서, 메모리 사용에 큰 영향을 주지 않는다면 한번에 처리하자
 * 성능이 크게 중요하지 않고, 버퍼 기능이 필요하다면 BufferedXxx를 사용하자
 *      BufferedXxx는 동기화 코드가 들어있어서 Thread-Safe하지만, 동기화가 필요한만큼 버퍼를 직접사용하는것보단 성능저하가 있다.
 * 성능이 중요하고 큰 파일을 나누어 처리해야 한다면, 버퍼를 직접 사용하자
 */
public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
