package com.invillia.poc.sales.serializer;

import com.invillia.poc.sales.domain.PurchaseData;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class PurchaseDataSerializer implements Serializer<PurchaseData> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, PurchaseData data) {

        int sizeOfId;
        int sizeOfIdProduct;
        int sizeOfIdAdditionalProduct;
        int sizeOfPrice;
        int sizeOfCreatedAt;
        int sizeOfUpdatedAt;

        byte [] serializedId;
        byte [] serializedIdProduct;
        byte [] serializedIdAdditionalProduct;
        byte [] serializedPrice;
        byte [] serializedCreatedAt;
        byte [] serializedUpdatedAt;

        try {
            if (data == null)
                return null;

            serializedId = data.getId().toString().getBytes(encoding);
            sizeOfId = serializedId.length;

            serializedIdProduct = data.getIdProduct().toString().getBytes(encoding);
            sizeOfIdProduct = serializedIdProduct.length;

            serializedIdAdditionalProduct = data.getIdAdditionalProduct().toString().getBytes(encoding);
            sizeOfIdAdditionalProduct = serializedIdAdditionalProduct.length;

            serializedPrice = data.getPrice().toString().getBytes(encoding);
            sizeOfPrice = serializedPrice.length;

            serializedCreatedAt = data.getCreatedAt().toString().getBytes(encoding);
            sizeOfCreatedAt = serializedCreatedAt.length;

            serializedUpdatedAt = data.getUpdatedAt().toString().getBytes(encoding);
            sizeOfUpdatedAt = serializedUpdatedAt.length;

            ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfId
                    + sizeOfIdProduct
                    + sizeOfIdAdditionalProduct
                    + sizeOfPrice
                    + sizeOfCreatedAt
                    + sizeOfUpdatedAt);

            byteBuffer.putInt(sizeOfId);
            byteBuffer.put(serializedId);
            byteBuffer.putInt(sizeOfIdProduct);
            byteBuffer.put(serializedIdProduct);
            byteBuffer.putInt(sizeOfIdAdditionalProduct);
            byteBuffer.put(serializedIdAdditionalProduct);
            byteBuffer.putInt(sizeOfPrice);
            byteBuffer.put(serializedPrice);
            byteBuffer.putInt(sizeOfCreatedAt);
            byteBuffer.put(serializedCreatedAt);
            byteBuffer.putInt(sizeOfUpdatedAt);
            byteBuffer.put(serializedUpdatedAt);

            return byteBuffer.array();

        }catch (Exception e){
            throw new SerializationException("Error when serializing PurchaseData to byte");
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, PurchaseData data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
