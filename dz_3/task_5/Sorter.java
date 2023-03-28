package homework.dz_3.task_5;


import java.io.*;
import java.util.*;


public class Sorter {


    public File sortFile(File dataFile) throws IOException {

        // Делим файл на более мелкие отсортированные фрагменты
        List<File> files = splitIntoSortedChunks(dataFile);

        // Объединяем отсортированные фрагменты, используя
        // приоритетную очередь
        File sortedFile = mergeSortedChunks(files);

        // Создаем новый файл для хранения отсортированных данных
        File newFile = new File(
                dataFile.getParentFile(), "sorted.txt" );
        newFile.createNewFile();

        // Записываем отсортированные данные в новый файл
        try (BufferedWriter writer = new BufferedWriter
                (new FileWriter(newFile))) {

            try (BufferedReader reader = new BufferedReader
                    (new FileReader(sortedFile))) {

                String line;
                while ((line = reader.readLine()) != null) {

                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        return newFile;
    }


    /**
     * Метод private List<File> splitIntoSortedChunks(File dataFile),
     * который разбивает входной файл на множество отсортированных
     * фрагментов и возвращает список файлов, содержащих эти фрагменты.
     * Он читает входной файл построчно, добавляет каждую строку в
     * список chunk, который представляет собой отсортированный фрагмент.
     * Когда список chunk достигает заданного размера (в данном случае,
     * 1 миллион), он сортирует его, сохраняет во временный файл
     * и добавляет этот файл в список files. Если входной файл
     * не делится нацело на заданный размер фрагмента, последний
     * фрагмент может быть меньше, но он все равно должен быть
     * отсортирован и сохранен. В конце метод возвращает список файлов,
     * содержащих отсортированные фрагменты.
     */
    private List<File> splitIntoSortedChunks(File dataFile)
            throws IOException {

        List<File> files = new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(dataFile));
        String line;
        List<Long> chunk = new ArrayList<>();
        int count = 0;
        while ((line = reader.readLine()) != null) {

            chunk.add(Long.parseLong(line));
            count++;
            if (count == 1_000_000) {

                Collections.sort(chunk);
                File file = File.createTempFile
                        ("chunk", ".tmp");
                try (PrintWriter writer = new PrintWriter(file)) {

                    for (Long num : chunk) {

                        writer.println(num);
                    }
                }
                files.add(file);
                chunk.clear();
                count = 0;
            }
        }
        // Обрабатываем последний кусок, если он не пустой
        if (!chunk.isEmpty()) {

            Collections.sort(chunk);
            File file = File.createTempFile("chunk", ".tmp");
            try (PrintWriter writer = new PrintWriter(file)) {

                for (Long num : chunk) {

                    writer.println(num);
                }
            }
            files.add(file);
        }
        reader.close();
        return files;
    }


    /**
     * Метод private File mergeSortedChunks(List<File> files)
     * throws IOException, который принимает список файлов, содержащих
     * отсортированные фрагменты, и возвращает файл с объединенными
     * отсортированными данными. Он создает приоритетную очередь из
     * итераторов, которые читают числа из каждого файла, и начинает
     * извлекать наименьшее значение из очереди. Затем он записывает
     * это значение в выходной файл и, если итератор, который предоставил
     * это значение, еще не исчерпал свой фрагмент, он добавляет его
     * обратно в очередь. Когда очередь становится пустой, метод
     * заканчивает запись и возвращает файл с объединенными
     * отсортированными данными.
     */
    private File mergeSortedChunks(List<File> files) throws IOException {

        if (files.size() == 1) {

            return files.get(0);
        }

        PriorityQueue<ChunkIterator> queue = new PriorityQueue<>();
        for (File file : files) {

            queue.add(new ChunkIterator(file));
        }
        File mergedFile = File.createTempFile
                ("sorted", ".tmp");
        PrintWriter writer = new PrintWriter(mergedFile);
        while (!queue.isEmpty()) {

            ChunkIterator iterator = queue.poll();
            writer.println(iterator.next());
            if (iterator.hasNext()) {

                queue.add(iterator);
            } else {

                iterator.close();
            }
        }
        writer.close();
        return mergedFile;
    }
}