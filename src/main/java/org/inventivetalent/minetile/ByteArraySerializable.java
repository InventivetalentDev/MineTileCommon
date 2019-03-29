package org.inventivetalent.minetile;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public interface ByteArraySerializable<T> {

	public T readFromByteArray(ByteArrayDataInput in);

	public T writeToByteArray(ByteArrayDataOutput out);

}
