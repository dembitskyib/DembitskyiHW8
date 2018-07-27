<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<table border="1">
					<thead>
						<tr>
							<th>Name</th>
							<th>Type</th>
							<th>Alcoholic</th>
							<th>Manufacturer</th>
							<th>Ingredients</th>
							<th>AlcoholFraction</th>
							<th>Transparency</th>
							<th>Filtered</th>
							<th>NutritionalValue</th>
							<th>Volume</th>
							<th>Material</th>
						</tr>
					</thead>
					<xsl:for-each select="beer/beerSort">
						<tr>
							<xsl:call-template name="PrintBeer" />
						</tr>
					</xsl:for-each>

				</table>
			</body>
		</html>
	</xsl:template>

	<xsl:template name="PrintBeer">
		<td>
			<xsl:value-of select="name" />
		</td>
		<td>
			<xsl:value-of select="type" />
		</td>
		<td>
			<xsl:value-of select="al" />
		</td>
		<td>
			<xsl:value-of select="manufacturer" />
		</td>
		<td>
			<xsl:value-of select="ingredients" />
		</td>
		<td>
			<xsl:value-of select="chars/alcoholFraction" />
		</td>
		<td>
			<xsl:value-of select="chars/transparency" />
		</td>
		<td>
			<xsl:value-of select="chars/filtered" />
		</td>
		<td>
			<xsl:value-of select="chars/nutritionalValue" />
		</td>
		<td>
			<xsl:value-of select="chars/spillMethod/volume" />
		</td>
		<td>
			<xsl:value-of select="chars/spillMethod/material" />
		</td>
	</xsl:template>
</xsl:stylesheet>