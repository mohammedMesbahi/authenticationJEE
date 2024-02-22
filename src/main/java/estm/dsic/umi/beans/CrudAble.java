package estm.dsic.umi.beans;

public interface CrudAble<T, L> {
    <T> get(<L>id);
    <T> create(<T>t);
    <T> update(<T>t);
    <T> delete(<T>t);
}
