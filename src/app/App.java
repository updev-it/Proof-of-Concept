package app;

import app.models.Location;
import app.models.Locations;
import app.models.LogEntry;
import app.models.LogEntryPeriod;
import app.models.LogEntryTemperature;
import app.models.Measurement;
import app.models.Thermometer;
import app.models.RelayFunctionality;
import app.models.ThermostatFunctionality;
import app.models.xml.PlugwiseHAXml;

public class App {

    public static String test = "<locations><name>Test</name></locations>";

    public static String apiTestOutput = "<locations> <location id='f33f9941f61248aca057ff0a66c13fb0'> <name>Woonkamer</name> <description>The room containing the (central) home thermostat.</description> <type>livingroom</type> <created_date>2018-11-25T10:49:34.312+01:00</created_date> <modified_date>2019-08-15T21:39:50.951+02:00</modified_date> <deleted_date></deleted_date> <preset>home</preset> <clients/> <appliances> <appliance id='121e22e2982f4434b3f99f43e448a308'/> <appliance id='2a64284c001340dc9dbee4c3157b1151'/> <appliance id='3fbbc4e3513d4621a98bc9e28ab76d15'/> <appliance id='7722001c8b1e4f5182c6fccbabe32d2d'/> <appliance id='81bb1f6978544b12ab5560dbddbd4be2'/> <appliance id='c40a214744874fb49fd6f81c5ac1d704'/> </appliances> <logs> <point_log id='0a06b39b9c42473684b2d034188aeb57'> <updated_date></updated_date> <type>electricity_produced</type> <unit>W</unit> <last_consecutive_log_date></last_consecutive_log_date> </point_log> <point_log id='12f284abec964b9c9c9826d43567c308'> <updated_date>2019-08-14T19:40:21.167+02:00</updated_date> <type>electricity_consumed</type> <unit>W</unit> <last_consecutive_log_date>2019-06-18T08:27:14.998+02:00</last_consecutive_log_date> <period start_date=\"2019-08-14T19:40:21.167+02:00\" end_date=\"2019-08-14T19:40:21.167+02:00\"> <measurement log_date=\"2019-08-14T19:40:21.167+02:00\">0.00</measurement> </period> </point_log> <point_log id='4c7c3a1e6ffd4df5aead0156f1abd160'> <updated_date></updated_date> <type>relay</type> <unit></unit> <last_consecutive_log_date></last_consecutive_log_date> </point_log> <point_log id='6bcfe8bab97f44a88921486773723156'> <updated_date>2019-08-04T21:43:13.851+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-05-08T12:48:17.750+02:00</last_consecutive_log_date> <period start_date=\"2019-08-04T21:43:13.851+02:00\" end_date=\"2019-08-04T21:43:13.851+02:00\"> <measurement log_date=\"2019-08-04T21:43:13.851+02:00\">10.00</measurement> </period> </point_log> <point_log id='99fcd83c80b04f2d888d26b16e7aac33'> <updated_date>2019-08-15T21:39:50.887+02:00</updated_date> <type>temperature</type> <unit></unit> <last_consecutive_log_date>2019-06-19T05:13:55.067+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:39:50.887+02:00\" end_date=\"2019-08-15T21:39:50.887+02:00\"> <measurement log_date=\"2019-08-15T21:39:50.887+02:00\">23.44</measurement> </period> </point_log> </logs> <actuator_functionalities> <relay_functionality id='6811a654f5104f66a9e2f1e137c92b0a'> <updated_date></updated_date> </relay_functionality> <thermostat_functionality id='bee5dffa3d2e4f05bc3d8d9f59f4a68e'> <updated_date>2019-08-04T21:43:13.852+02:00</updated_date> <type>thermostat</type> <setpoint>10</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='6f887989b7a44d1cb65acd85b8b782d2'> <name>Slaapkamer Eva</name> <description></description> <type>bedroom</type> <created_date>2018-11-25T14:48:51.308+01:00</created_date> <modified_date>2019-08-15T21:42:36.806+02:00</modified_date> <deleted_date></deleted_date> <preset>asleep</preset> <clients/> <appliances> <appliance id='2b44119a4e44412b877060b635c1bb7e'/> <appliance id='d825e89bb9f440f1a9db4db064d6c7df'/> </appliances> <logs> <point_log id='735e82bb372b43cd8a17a484422f7355'> <updated_date>2019-08-15T21:42:36.791+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:12:39.673+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:42:36.791+02:00\" end_date=\"2019-08-15T21:42:36.791+02:00\"> <measurement log_date=\"2019-08-15T21:42:36.791+02:00\">22.50</measurement> </period> </point_log> <point_log id='e25f163e41fe4b8a8996f674aaaabd5f'> <updated_date>2019-06-13T11:36:05.835+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-05-27T10:58:09.547+02:00</last_consecutive_log_date> <period start_date=\"2019-06-13T11:36:05.835+02:00\" end_date=\"2019-06-13T11:36:05.835+02:00\"> <measurement log_date=\"2019-06-13T11:36:05.835+02:00\">14.50</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='8075482cbe7b4bd8960dd447ff453914'> <updated_date>2019-06-13T11:36:05.835+02:00</updated_date> <type>thermostat</type> <setpoint>14.5</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='6b08a3a7322643cf8c5c435bd4a26391'> <name>Badkamer zolder</name> <description></description> <type>bathroom</type> <created_date>2018-11-25T14:42:31.075+01:00</created_date> <modified_date>2019-08-15T21:33:26.579+02:00</modified_date> <deleted_date></deleted_date> <preset>away</preset> <clients/> <appliances> <appliance id='4701a532c09a4b0cb913ba78e9919b00'/> </appliances> <logs> <point_log id='67b00480329342eb87a413cb99b9a5be'> <updated_date>2019-08-15T19:30:00.634+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-06-18T19:30:00.345+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T19:30:00.634+02:00\" end_date=\"2019-08-15T19:30:00.634+02:00\"> <measurement log_date=\"2019-08-15T19:30:00.634+02:00\">17.00</measurement> </period> </point_log> <point_log id='b07abb086a3947a8b7776d22b8a27407'> <updated_date>2019-08-15T21:33:26.561+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:14:18.588+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:33:26.561+02:00\" end_date=\"2019-08-15T21:33:26.561+02:00\"> <measurement log_date=\"2019-08-15T21:33:26.561+02:00\">23.05</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='f37c6fcaa5bd4d86a547ea18053402b1'> <updated_date>2019-08-15T19:30:00.635+02:00</updated_date> <type>thermostat</type> <setpoint>17</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='271c289a795842358b080906e1dc1649'> <name>Badkamer</name> <description></description> <type>bathroom</type> <created_date>2018-11-25T16:57:52.150+01:00</created_date> <modified_date>2019-08-15T21:35:46.317+02:00</modified_date> <deleted_date></deleted_date> <preset>away</preset> <clients/> <appliances> <appliance id='3a1c5b08aea240c9a245b2e11f9177d9'/> </appliances> <logs> <point_log id='45137680129d42829a3a05bafbb18b77'> <updated_date>2019-08-15T08:30:00.366+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-06-18T22:00:02.553+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T08:30:00.366+02:00\" end_date=\"2019-08-15T08:30:00.366+02:00\"> <measurement log_date=\"2019-08-15T08:30:00.366+02:00\">21.00</measurement> </period> </point_log> <point_log id='6c4eded238b74a3e8eef3984a4644a2c'> <updated_date>2019-08-15T21:35:46.300+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:08:54.774+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:35:46.300+02:00\" end_date=\"2019-08-15T21:35:46.300+02:00\"> <measurement log_date=\"2019-08-15T21:35:46.300+02:00\">23.02</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='148d0a99bb1a4a608bc00ae4ab155ebd'> <updated_date>2019-08-15T08:30:00.367+02:00</updated_date> <type>thermostat</type> <setpoint>21</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='b23c6cd50ed843eca8706942c16e1085'> <name>Werkkamer</name> <description></description> <type>office</type> <created_date>2018-11-25T12:01:26.077+01:00</created_date> <modified_date>2019-08-15T21:36:41.375+02:00</modified_date> <deleted_date></deleted_date> <preset>asleep</preset> <clients/> <appliances> <appliance id='c6bebf8e69394669bcb555a49a175461'/> <appliance id='c6d1adbbfda24b82ad40b309805294a6'/> </appliances> <logs> <point_log id='4904bc0c6e9e4586add448850f380e1b'> <updated_date>2019-08-10T14:38:10.425+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-04-13T12:57:38.727+02:00</last_consecutive_log_date> <period start_date=\"2019-08-10T14:38:10.425+02:00\" end_date=\"2019-08-10T14:38:10.425+02:00\"> <measurement log_date=\"2019-08-10T14:38:10.425+02:00\">10.00</measurement> </period> </point_log> <point_log id='edb6718327d342b59f93130776020bd8'> <updated_date>2019-08-15T21:36:41.358+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:18:51.880+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:36:41.358+02:00\" end_date=\"2019-08-15T21:36:41.358+02:00\"> <measurement log_date=\"2019-08-15T21:36:41.358+02:00\">24.50</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='1e86e990d4d04337a84746d459752ac8'> <updated_date>2019-08-10T14:38:09.423+02:00</updated_date> <type>thermostat</type> <setpoint>10</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='254ebf8b7f504919a1d72575497ed956'> <name>Slaapkamer Sophie</name> <description></description> <type>bedroom</type> <created_date>2018-11-25T14:30:37.431+01:00</created_date> <modified_date>2019-08-15T21:31:57.155+02:00</modified_date> <deleted_date></deleted_date> <preset>asleep</preset> <clients/> <appliances> <appliance id='85be32d0c6e74d5988b0160a279d50db'/> <appliance id='934ac34e86bc462ea56f650d9c6a679e'/> </appliances> <logs> <point_log id='2a4be3ab8f464f529e24a9c8aff110e1'> <updated_date>2019-08-15T21:31:57.135+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:14:34.716+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:31:57.135+02:00\" end_date=\"2019-08-15T21:31:57.135+02:00\"> <measurement log_date=\"2019-08-15T21:31:57.135+02:00\">23.30</measurement> </period> </point_log> <point_log id='9a67c0db322b401c8276c73366e302ba'> <updated_date>2019-04-13T12:57:53.226+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-03-18T11:23:08.772+01:00</last_consecutive_log_date> <period start_date=\"2019-04-13T12:57:53.226+02:00\" end_date=\"2019-04-13T12:57:53.226+02:00\"> <measurement log_date=\"2019-04-13T12:57:53.226+02:00\">16.00</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='b0fed579e5e54df9b7df26a1f9bb6f54'> <updated_date>2019-04-13T12:57:51.373+02:00</updated_date> <type>thermostat</type> <setpoint>16</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> <location id='e5a3a4e0df164b5c83750c53bb5a4e38'> <name>Home</name> <description></description> <type>building</type> <created_date>2018-10-30T12:51:43.449+01:00</created_date> <modified_date>2019-08-15T21:28:48.896+02:00</modified_date> <deleted_date></deleted_date> <preset>home</preset> <clients/> <appliances/> <logs> <point_log id='6cbe44ed319f4e4eb47c368a8d9687df'> <updated_date>2019-08-15T21:28:47+02:00</updated_date> <type>outdoor_temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-08-15T21:28:47+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:28:47+02:00\" end_date=\"2019-08-15T21:28:47+02:00\"> <measurement log_date=\"2019-08-15T21:28:47+02:00\">17.25</measurement> </period> <thermo_meter id='207ea64979c0430b8c470d31ec44d8d5'/> </point_log> <point_log id='982df00abcba444ea882c6bbcb3c061b'> <updated_date>2019-08-15T21:28:47+02:00</updated_date> <type>solar_irradiance</type> <unit>W_m2</unit> <last_consecutive_log_date>2019-08-15T21:28:47+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:28:47+02:00\" end_date=\"2019-08-15T21:28:47+02:00\"> <measurement log_date=\"2019-08-15T21:28:47+02:00\">0.00</measurement> </period> <irradiance_meter id='95186c8f8bc84cfb9a5d42f9c5c34536'/> </point_log> <point_log id='ce071d5a195e4c50a0a68419bbdf42c1'> <updated_date>2019-08-15T21:28:47+02:00</updated_date> <type>humidity</type> <unit>RH</unit> <last_consecutive_log_date>2019-08-15T21:28:47+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:28:47+02:00\" end_date=\"2019-08-15T21:28:47+02:00\"> <measurement log_date=\"2019-08-15T21:28:47+02:00\">87.00</measurement> </period> <hygro_meter id='faa0a553854b417aaf1e2781d0434464'/> </point_log> <point_log id='d61932fa60cb4b319dcdd9174a5259d1'> <updated_date>2019-08-15T21:28:47+02:00</updated_date> <type>wind_vector</type> <unit>m_s</unit> <last_consecutive_log_date>2019-08-15T21:28:47+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:28:47+02:00\" end_date=\"2019-08-15T21:28:47+02:00\"> <measurement log_date=\"2019-08-15T21:28:47+02:00\">0.00</measurement> </period> <wind_vector id='7c96b8d084e64c9aae3dadc62f1c9ea6'/> </point_log> </logs> <actuator_functionalities/> </location> <location id='146a9df6d0004beca1695c7d61a8ac75'> <name>Slaapkamer</name> <description></description> <type>bedroom</type> <created_date>2018-11-25T16:48:41.516+01:00</created_date> <modified_date>2019-08-15T21:41:42.581+02:00</modified_date> <deleted_date></deleted_date> <preset>no_frost</preset> <clients/> <appliances> <appliance id='0e5b6227f04f4e65888471e58b57a791'/> <appliance id='1123b65a5e48419fba04310dc9022eb4'/> <appliance id='6414fd7243e143cf805261ef2e5977f5'/> </appliances> <logs> <point_log id='31bc1aee493043ef985c0ca8f03acf4b'> <updated_date>2019-06-25T21:40:36.406+02:00</updated_date> <type>thermostat</type> <unit>C</unit> <last_consecutive_log_date>2019-05-08T12:56:58.456+02:00</last_consecutive_log_date> <period start_date=\"2019-06-25T21:40:36.406+02:00\" end_date=\"2019-06-25T21:40:36.406+02:00\"> <measurement log_date=\"2019-06-25T21:40:36.406+02:00\">15.00</measurement> </period> </point_log> <point_log id='7c39965d328e4e668e3f81e2cf6f3320'> <updated_date>2019-08-15T21:41:42.564+02:00</updated_date> <type>temperature</type> <unit>C</unit> <last_consecutive_log_date>2019-06-19T05:18:42.790+02:00</last_consecutive_log_date> <period start_date=\"2019-08-15T21:41:42.564+02:00\" end_date=\"2019-08-15T21:41:42.564+02:00\"> <measurement log_date=\"2019-08-15T21:41:42.564+02:00\">21.30</measurement> </period> </point_log> </logs> <actuator_functionalities> <thermostat_functionality id='11a1c21138644e2f99c87a935d5a6b5c'> <updated_date>2019-06-25T21:40:36.407+02:00</updated_date> <type>thermostat</type> <setpoint>15</setpoint> <lower_bound>0</lower_bound> <upper_bound>99.99</upper_bound> <resolution>0.01</resolution> <regulations><open_therm_regulation id='7d3031821fa84a24841acca0fcc09889'/></regulations> </thermostat_functionality> </actuator_functionalities> </location> </locations>";
    
    // public void test() {
    //     PlugwiseHAXml xstream = new PlugwiseHAXml();

    //     // Create locations object
    //     Locations locations = new Locations();

    //     // Create 2 locations (woonkamer & badkamer)
    //     Location location1 = new Location("location1_id", "woonkamer");
    //     Location location2 = new Location("location2_id", "badkamer");

    //     // Add 2 appliances to location woonkamer
    //     location1.addAppianceId("appliance1_id");
    //     location1.addAppianceId("appliance2_id");

    //     // Add 1 appliance to location badkamer
    //     location2.addAppianceId("appliance3_id");

    //     // Create 2 ActuatorFunctionalities (i.e. ThermostatFunctionality and
    //     // RelayFunctionality)
    //     ThermostatFunctionality tfOne = new ThermostatFunctionality("thermostatid_1");
    //     RelayFunctionality rfOne = new RelayFunctionality("relayid_1");

    //     // Configure ThermostatFunctionality
    //     tfOne.setUpdatedDate("2019-05-08T12:48:17.751+02:00");
    //     tfOne.setLowerBound(0.0);
    //     tfOne.setUpperBound(99.99);
    //     tfOne.setResolution(0.01);
    //     tfOne.setSetpoint(18.0);
    //     tfOne.setType("thermostat");

    //     // Configure RelayFunctionality
    //     rfOne.setUpdatedDate("");

    //     // Add the ActuatorFunctionalities to woonkamer and badkamer
    //     location1.addThermostatFunctionality(tfOne);
    //     location2.addRelayFunctionality(rfOne);

    //     // Add log entries to woonkamer
    //     LogEntry logEntry1 = new LogEntryTemperature("2a4be3ab8f464f529e24a9c8aff110e1");
    //     LogEntry logEntry3 = new LogEntryTemperature("test");
    //     LogEntryPeriod logEntryPeriod1 = new LogEntryPeriod("2019-08-03T11:30:46.147+02:00",
    //             "2019-08-03T11:30:46.147+02:00");
    //     Measurement measurement1 = new Measurement("24.8");
    //     measurement1.setLogDate("2019-08-03T11:30:46.147+02:00");
    //     logEntryPeriod1.setMeasurement(measurement1);
    //     logEntry1.setUpdatedDate("2019-08-03T11:30:46.147+02:00");

    //     logEntry1.setLogEntryPeriod(logEntryPeriod1);
    //     location1.addLogEntry(logEntry1);

    //     // Add log entries to badkamer
    //     LogEntry logEntry2 = new LogEntryTemperature("b65a260dbfba4081b1c35ed3b699370b");
    //     logEntry2.setUpdatedDate("2019-08-03T11:30:46.147+02:00");
    //     logEntry2.setLogEntryPeriod(
    //             new LogEntryPeriod("2019-08-03T11:30:46.147+02:00", "2019-08-03T11:30:46.147+02:00"));
    //     location2.addLogEntry(logEntry2);

    //     // Add both locations to the root locations object
    //     locations.put(location1.getId(), location1);
    //     locations.put(location2.getId(), location2);

    //     String xml1 = xstream.toXML(locations);
    //     Locations locations_r = (Locations) xstream.fromXML(xml1);
    //     String xml1r = xstream.toXML(locations_r);

    //     System.out.println(xml1);

    //     xstream.prettyPrint(locations_r);

    //     System.out.println();
    //     System.out.println("===============================");

    //     if (xml1.intern() == xml1r.intern()) {
    //         System.out.println("XML DATA MATCHES");
    //     } else {
    //         System.out.println("XML DATA DOES NOT MATCH");
    //     }

    //     System.out.println();
    // }

    public static void main(String[] args) throws Exception {
        PlugwiseHAXml xstream = new PlugwiseHAXml();
        Locations locations_r = (Locations) xstream.fromXML(App.apiTestOutput);

        xstream.prettyPrint(locations_r);
    }
}