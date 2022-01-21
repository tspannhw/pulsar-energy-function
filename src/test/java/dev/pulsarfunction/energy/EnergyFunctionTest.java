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

    public static String JSON_STRING = "{\"current\": 0.031246, \"voltage\": 118.191585, \"power\": 1.687476, \"total\": 14.566, \"sw_ver\": \"1.2.5 Build 171206 Rel.085954\", \"hw_ver\": \"1.0\", \"type\": \"IOT.SMARTPLUGSWITCH\", \"model\": \"HS110(US)\", \"mac\": \"50:C7:BF:B1:95:D5\", \"deviceId\": \"8006ECB1D454C4428953CB2B34D9292D18A6DB0E\", \"hwId\": \"60FF6B258734EA6880E186F8C96DDC61\", \"fwId\": \"00000000000000000000000000000000\", \"oemId\": \"FFF22CFF774A0B89F7624BFC6F50D5DE\", \"alias\": \"Fan\", \"dev_name\": \"Wi-Fi Smart Plug With Energy Monitoring\", \"icon_hash\": \"\", \"relay_state\": 1, \"on_time\": 5999378, \"active_mode\": \"schedule\", \"feature\": \"TIM:ENE\", \"updating\": 0, \"rssi\": -68, \"led_off\": 0, \"latitude\": 40.268272, \"longitude\": -74.529139, \"day1\": 0.225, \"day2\": 0.225, \"day3\": 0.224, \"day4\": 0.232, \"day5\": 0.24, \"day6\": 0.253, \"day7\": 0.243, \"day8\": 0.235, \"day9\": 0.23, \"day10\": 0.232, \"day11\": 0.187, \"day12\": 0.037, \"day13\": 0.152, \"day14\": 0.037, \"day15\": 0.035, \"day16\": 0.033, \"day17\": 0.032, \"day18\": 0.093, \"day19\": 0.228, \"day20\": 0.234, \"day21\": 0.201, \"index\": 18, \"zone_str\": \"(UTC-05:00) Eastern Daylight Time (US & Canada)\", \"tz_str\": \"EST5EDT,M3.2.0,M11.1.0\", \"dst_offset\": 60, \"month1\": 3.608, \"host\": \"192.168.1.252\", \"current_consumption\": 1.687476, \"devicetime\": \"01/21/2022 17:22:33\", \"ledon\": true, \"end\": \"1642803753.5322309\", \"te\": \"0.1876220703125\", \"systemtime\": \"01/21/2022 17:22:33\", \"cpu\": 1.5, \"memory\": 10.8, \"diskusage\": \"85647.7\", \"uuid\": \"20220121222233_a79c318e-f4ad-493a-a424-a8af9fbfd80c\", \"macaddress\": null}";

    /**
     *
     * @param msg
     */
    protected void log(String msg) {
        if (ctx != null && ctx.getLogger() != null) {
            ctx.getLogger().info(String.format("Function: [%s, id: %s, instanceId: %d of %d] %s",
                    ctx.getFunctionName(), ctx.getFunctionId(), ctx.getInstanceId(), ctx.getNumInstances(), msg));
        }
    }

    @Test
    public void testEnergyFunction() {
        EnergyFunction func = new EnergyFunction();
        Device output = func.process(JSON_STRING, mock(Context.class));
        System.out.println(output.toString());
        //Assert.assertEquals(output, "Positive");
    }

    /**
     * @param args   string arguments
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
                    .autoAck(true)
                    .exposePulsarAdminClientEnabled(true)
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
