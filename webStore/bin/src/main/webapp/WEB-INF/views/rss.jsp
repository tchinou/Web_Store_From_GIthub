<%@include file="master.jsp"%>
<ct:header></ct:header>
<ct:body>
	<div id="center" class="column">
		<div class="content">
			<table width="90%" border="0" cellspacing="4" align="center"
				cellpadding="4">
				<tbody>
					<tr>
						<td colspan="2" height="19"><b>Starting Feeds</b></td>
					</tr>
					<tr>
						<td width="20%" height="23" valign="top">
							<div align="center">
								<a href="${xml}/sample.xml"><img src="${images}/feed-7.gif"
									width="48" border="0"></a>
							</div>
						</td>
						<td width="80%" height="23" valign="top">Cell Phones Feed 1</td>
					</tr>
					<tr>
						<td width="20%" height="15" valign="top">
							<div align="center">
								<a href="sample-feed.xml"><img src="${images}/feed-7.gif"
									width="48" height="14" border="0"></a>
							</div>
						</td>
						<td width="80%" height="15" valign="top">Notebooks Feed 2</td>
					</tr>
				</tbody>
			</table>

			<table width="90%" border="0" cellspacing="4" align="center"
				cellpadding="4">
				<tbody>
					<tr>
						<td colspan="2" height="19"><b>Other Related Feeds </b></td>
					</tr>
					<tr>
						<td width="20%" height="23" valign="top">
							<div align="center">
								<a href="blog-feed.xml"><img src="${images}/feed-7.gif"
									width="48" border="0"></a>
							</div>
						</td>
						<td width="80%" height="23" valign="top">FeedForAll Blog Feed</td>
					</tr>
					<tr>
						<td width="20%" height="15" valign="top">
							<div align="center">
								<a href="http://www.rss-specifications.com/blog-feed.xml"><img
									src="${images}/feed-7.gif" width="48" height="14" border="0"></a>
							</div>
						</td>
						<td width="80%" height="15" valign="top">RSS Blog - RSS
							Specifications Website</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<ct:left></ct:left>
	<ct:right></ct:right>
</ct:body>
<ct:footer></ct:footer>