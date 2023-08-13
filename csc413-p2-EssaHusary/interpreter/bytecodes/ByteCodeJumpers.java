package interpreter.bytecodes;

public interface ByteCodeJumpers extends ByteCode {

    public String getValue();

    public void setResolvedAddress(int resolvedAddress);

}
