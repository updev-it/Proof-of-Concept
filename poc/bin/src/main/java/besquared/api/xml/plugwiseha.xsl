<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@*">
        <xsl:element name="{name()}">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="*/services/*">
        <xsl:element name="service">
            <xsl:element name="type">
                <xsl:value-of select ="local-name()"/>
            </xsl:element>            
            <xsl:apply-templates select=".//*[name()='point_log' or name()='interval_log']"/>
            <xsl:element name="type">
                <xsl:value-of select ="@log_type"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="//*[name()='point_log' or name()='interval_log']">
        <xsl:element name="log">
            <xsl:element name="id">
                <xsl:value-of select ="@id"/>
            </xsl:element>
            <xsl:element name="log_type">
                <xsl:value-of select ="local-name()"/>
            </xsl:element>
            <xsl:element name="type">
                <!-- <xsl:value-of select ="../../@log_type"/> -->
                <xsl:value-of select ="ancestor::*[2]/@log_type"/>                
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="//protocols" />

</xsl:stylesheet>