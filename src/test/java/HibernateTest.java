import lombok.val;
import org.junit.jupiter.api.Test;
import vetorlog.model.ExampleModel;

import java.util.HashMap;
import java.util.List;

class HibernateTest extends ResourceLocalTestConfig {
    @Test
    void insertTest() {
        for(int i=0; i < 100; i++) {
            ExampleModel model = new ExampleModel();
            model.setValueString("Teste " + (int) (Math.random() * 1000));
            model.setValueDouble(Math.random() * 100000);
            dbManager.insert(model);
        }
    }

    @Test
    void deleteTest() {
        ExampleModel model = dbManager.first(ExampleModel.class);
        dbManager.delete(model);
    }

    @Test
    void updateTest() {
        ExampleModel model = dbManager.first(ExampleModel.class);
        model.setValueString("Modificado");
        model.setValueDouble(-1);
        dbManager.update(model);
    }

    @Test
    void queryTest() {
        val em = dbManager.getEntityManager();
        List<ExampleModel> models = dbManager.find(em.createQuery(
                "SELECT m FROM ExampleModel AS m", ExampleModel.class), 0, 100);

        for(val model : models)
            System.out.println(model.getValueString() + ": " + model.getValueDouble());
    }

    @Test
    void queryTestParams() {
        val em = dbManager.getEntityManager();
        val query = em.createQuery(
                "SELECT m FROM ExampleModel AS m WHERE m.valueDouble < :param",
                ExampleModel.class);
        query.setParameter("param", 50000.0);

        List<ExampleModel> models = dbManager.find(query, 0, 100);

        System.out.println(String.format("max x cout: %d x %d", dbManager.count(ExampleModel.class), models.size()));
        for(val model : models)
            System.out.println(model.getValueString() + ": " + model.getValueDouble());
    }

    @Test
    void queryTestParamsMap() {
        val queryString = "SELECT m FROM ExampleModel AS m WHERE m.valueDouble < :param";
        val queryParameters = new HashMap<String, Object>();
        queryParameters.put("param", 50000.0);

        List<ExampleModel> models = dbManager.find(queryString, queryParameters, 0, 100);

        System.out.println(String.format("max x cout: %d x %d", dbManager.count(ExampleModel.class), models.size()));
        for(val model : models)
            System.out.println(model.getValueString() + ": " + model.getValueDouble());
    }
}
