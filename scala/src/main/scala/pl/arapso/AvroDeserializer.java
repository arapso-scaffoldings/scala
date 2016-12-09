package pl.arapso;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import pl.roqad.common.data.model.http.Request;


public class AvroDeserializer {

    public void main(String[] args) throws IOException {
        File file = new File("/home/damian/tmp/avro/pro/2016-11-20/tracker1000.avro");
        DatumReader<Request> userDatumReader = new SpecificDatumReader<Request>(Request.class);
        DataFileReader<Request> dataFileReader = new DataFileReader<Request>(file, userDatumReader);
        Request emp = null;


        while (dataFileReader.hasNext()) {
            emp = dataFileReader.next();
            System.out.println(emp);
        }
    }
}


