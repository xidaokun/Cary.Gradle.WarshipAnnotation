package org.warship.annotation

class ProcessorHelper {
    static void create(path, className, contnet) {
        System.out.println("path:"+path);
        System.out.println("className:"+className);
        System.out.println("content:"+contnet);
        File file = new File(path, className)
        if(!file.exists()) {
            file.createNewFile()
        }
        file.write(contnet, 'UTF-8')
    }
}