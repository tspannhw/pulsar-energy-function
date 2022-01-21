package dev.pulsarfunction.energy;

import dev.pulsarfunction.energy.EnergyFunction;
import org.apache.pulsar.common.functions.FunctionConfig;
import org.apache.pulsar.common.io.SourceConfig;
import org.apache.pulsar.functions.LocalRunner;
import org.junit.Assert;
import org.junit.Test;
import org.apache.pulsar.functions.api.Context;

import java.util.Collections;

import static org.mockito.Mockito.mock;

public class EnergyFunctionTest {

    protected Context ctx;

    protected void init(Context ctx) {
        this.ctx = ctx;
    }

    protected void log(String msg) {
        if (ctx != null && ctx.getLogger() != null) {
            ctx.getLogger().info(String.format("Function: [%s, id: %s, instanceId: %d of %d] %s",
                    ctx.getFunctionName(), ctx.getFunctionId(), ctx.getInstanceId(), ctx.getNumInstances(), msg));
        }
    }

    @Test
    public void testEnergyFunction() {
        EnergyFunction func = new EnergyFunction();
        String output = func.process("this is great.", mock(Context.class));
        //Assert.assertEquals(output, "Positive");
    }

    /**
     * @param args
     * @throws Exception
     */
        public static void main(String[] args) throws Exception {

            FunctionConfig functionConfig = FunctionConfig.builder()
                    .className(EnergyFunction.class.getName())
                    .inputs(Collections.singleton("persistent://public/default/energy"))
                    .name("Energy")
                    .tenant("public")
                    .namespace("default")
                    .runtime(FunctionConfig.Runtime.JAVA)
                    .cleanupSubscription(true)
                    .build();

            // nvidia-desktop
            LocalRunner localRunner = LocalRunner.builder()
                    .brokerServiceUrl("pulsar://pulsar1:6650")
                    .functionConfig(functionConfig)
                    .build();

            localRunner.start(false);

            Thread.sleep(30000);
            localRunner.stop();
            System.exit(0);
        }
}
