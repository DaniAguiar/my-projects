package com.invillia.poc.notegenerator.deserializer;

import com.invillia.poc.notegenerator.domain.PurchaseData;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PurchaseDataDeserializer implements Deserializer {
    private String encoding = "UTF8";

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Recebido nulo pelo deserializador");
                return null;
            }

            ByteBuffer byteBuffer = ByteBuffer.wrap(data);

            int sizeOfId = byteBuffer.getInt();
            byte[] idBytes = new byte[sizeOfId];
            byteBuffer.get(idBytes);
            Long deserializedId = Long.parseLong(new String(idBytes, encoding));

            int sizeOfIdProduct = byteBuffer.getInt();
            byte[] idProductBytes = new byte[sizeOfIdProduct];
            byteBuffer.get(idProductBytes);
            Long deserializedIdProduct = Long.parseLong(new String(idProductBytes, encoding));

            int sizeOfIdAdditionalProduct = byteBuffer.getInt();
            byte[] idAdditionalProductBytes = new byte[sizeOfIdAdditionalProduct];
            byteBuffer.get(idAdditionalProductBytes);
            Long deserializedIdAdditionalProduct = Long.parseLong(new String(idAdditionalProductBytes, encoding));

            int sizeOfPrice = byteBuffer.getInt();
            byte[] priceBytes = new byte[sizeOfPrice];
            byteBuffer.get(priceBytes);
            Double deserializedPrice = Double.parseDouble(new String(priceBytes, encoding));

            int sizeOfCreatedAt = byteBuffer.getInt();
            byte[] createdAtBytes = new byte[sizeOfCreatedAt];
            byteBuffer.get(createdAtBytes);
            String deserializedCreatedAt = new String(createdAtBytes, encoding);

            int sizeOfUpdatedAt = byteBuffer.getInt();
            byte[] updatedAtBytes = new byte[sizeOfUpdatedAt];
            byteBuffer.get(updatedAtBytes);
            String deserializedUpdatedAt = new String(updatedAtBytes, encoding);


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            return new PurchaseData(
                    deserializedId,
                    deserializedIdProduct,
                    deserializedIdAdditionalProduct,
                    deserializedPrice,
                    dateFormat.format(deserializedCreatedAt),
                    dateFormat.format(deserializedUpdatedAt));

        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to PurchaseData");
        }
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
