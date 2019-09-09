<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:include href="identity.xslt"/>

    <xsl:template match="@*">
        <xsl:element name="{name()}">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="logs">
        <xsl:apply-templates/>
    </xsl:template>
    
     <xsl:template match="//period">
      <xsl:apply-templates select="node()"/>
     </xsl:template>
     
     <xsl:template match="//measurement">
     <xsl:element name="measurement">
      <xsl:copy-of select="text()"></xsl:copy-of>   
     </xsl:element>      
     </xsl:template>
  
</xsl:stylesheet>
