{
	"patcher" : 	{
		"fileversion" : 1,
		"appversion" : 		{
			"major" : 6,
			"minor" : 1,
			"revision" : 3,
			"architecture" : "x86"
		}
,
		"rect" : [ 741.0, 79.0, 320.0, 286.0 ],
		"bglocked" : 0,
		"openinpresentation" : 0,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 5.0, 5.0 ],
		"gridsnaponopen" : 0,
		"statusbarvisible" : 2,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"description" : "",
		"digest" : "",
		"tags" : "",
		"boxes" : [ 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-7",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 180.0, 10.0, 78.0, 18.0 ],
					"presentation_rect" : [ 178.0, 12.0, 0.0, 0.0 ],
					"text" : ":show view"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-6",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 95.0, 10.0, 78.0, 18.0 ],
					"presentation_rect" : [ 95.0, 9.0, 0.0, 0.0 ],
					"text" : ":show view"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-4",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 160.0, 250.0, 84.0, 20.0 ],
					"text" : "print /out~"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-5",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 10.0, 10.0, 78.0, 18.0 ],
					"text" : ":show model"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-3",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 10.0, 250.0, 84.0, 20.0 ],
					"text" : "print /buffers~"
				}

			}
, 			{
				"box" : 				{
					"args" : [ "out~" ],
					"id" : "obj-2",
					"maxclass" : "bpatcher",
					"name" : "omod.output~.maxpat",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 160.0, 35.0, 140.0, 210.0 ]
				}

			}
, 			{
				"box" : 				{
					"args" : [ "buffers~" ],
					"id" : "obj-1",
					"maxclass" : "bpatcher",
					"name" : "omod.bufferplay~.maxpat",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 10.0, 35.0, 140.0, 210.0 ]
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"destination" : [ "obj-3", 0 ],
					"disabled" : 0,
					"hidden" : 0,
					"source" : [ "obj-1", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-4", 0 ],
					"disabled" : 0,
					"hidden" : 0,
					"source" : [ "obj-2", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1", 0 ],
					"disabled" : 0,
					"hidden" : 0,
					"source" : [ "obj-5", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1", 0 ],
					"disabled" : 0,
					"hidden" : 0,
					"source" : [ "obj-6", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-2", 0 ],
					"disabled" : 0,
					"hidden" : 0,
					"source" : [ "obj-7", 0 ]
				}

			}
 ],
		"parameters" : 		{
			"obj-2::obj-13::obj-204" : [ "live.numbox[15]", "live.numbox", 0 ],
			"obj-1::obj-13::obj-31" : [ "live.numbox[6]", "live.numbox[4]", 0 ],
			"obj-1::obj-13::obj-36" : [ "live.grid[3]", "live.grid", 0 ],
			"obj-1::obj-13::obj-18::obj-204" : [ "live.numbox[3]", "live.numbox", 0 ],
			"obj-2::obj-13::obj-36" : [ "live.numbox[1]", "live.numbox[1]", 0 ],
			"obj-1::obj-13::obj-18::obj-18::obj-4" : [ "live.slider", "live.slider", 0 ],
			"obj-1::obj-13::obj-12" : [ "live.slider[1]", "live.slider", 0 ],
			"obj-2::obj-13::obj-6" : [ "live.slider[7]", "live.slider", 0 ]
		}
,
		"dependency_cache" : [ 			{
				"name" : "omod.bufferplay~.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.bufferplay~",
				"patcherrelativepath" : ".",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "mxj oovu.Module.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.module.dispatch.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.route.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.re.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.messaging.call.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/messaging",
				"patcherrelativepath" : "../../abstractions/messaging",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.queue.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.messaging.reply.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/messaging",
				"patcherrelativepath" : "../../abstractions/messaging",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.get.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.silentdict.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.rv.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.common.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.this.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.module.constructor.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.environment.js",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "TEXT",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.randdelay.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.module.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "mxj oovu.Proxy.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.updated.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.argssetup.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.bufferplay~.model.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.bufferplay~",
				"patcherrelativepath" : ".",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "mxj oovu.Method.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.attribute.constructor.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.attribute.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.attribute.dispatch.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.generic.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "mxj oovu.Property.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.range.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.extremum.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.map.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.clip.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.range2centerwidth.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.lineseg.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.centerwidth2range.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.range.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.property.dispatch.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.value.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.bundle.dsp.sender.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/bundles",
				"patcherrelativepath" : "../../abstractions/bundles",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.integer.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.source.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.source.poly~.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.lazyproperty.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/core",
				"patcherrelativepath" : "../../abstractions/core",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.dictsub.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.decimal.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.push.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.push.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.source.dispatch.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.dsp.8chlevels.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/dsp",
				"patcherrelativepath" : "../../abstractions/dsp",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.bundle.dsp.base.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/bundles",
				"patcherrelativepath" : "../../abstractions/bundles",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.boolean.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.boolean.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.bufferplay~.voix.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.bufferplay~",
				"patcherrelativepath" : ".",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.bufferplay~.voix.kernel.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.bufferplay~",
				"patcherrelativepath" : ".",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.dsp.panner~.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/dsp",
				"patcherrelativepath" : "../../abstractions/dsp",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.show.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.filesystempath.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.bufferplay~.view.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.bufferplay~",
				"patcherrelativepath" : ".",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.guibackground.js",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/gui",
				"patcherrelativepath" : "../../abstractions/gui",
				"type" : "TEXT",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.gui.meters.8x.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/gui",
				"patcherrelativepath" : "../../abstractions/gui",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.gui.send.dynamic.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/gui",
				"patcherrelativepath" : "../../abstractions/gui",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.gui.send.1x.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/gui",
				"patcherrelativepath" : "../../abstractions/gui",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.makeumenu.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.output~.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.output~",
				"patcherrelativepath" : "../omod.output~",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.output~.model.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.output~",
				"patcherrelativepath" : "../omod.output~",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.bundle.dsp.receiver.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/bundles",
				"patcherrelativepath" : "../../abstractions/bundles",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.destination.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.destination.dispatch.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.obj.audionode.constructor.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/internals",
				"patcherrelativepath" : "../../abstractions/internals",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.push.destination.poly~.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/pushpull",
				"patcherrelativepath" : "../../abstractions/pushpull",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.output~.voix.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.output~",
				"patcherrelativepath" : "../omod.output~",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.datatype.intarray.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/datatypes",
				"patcherrelativepath" : "../../abstractions/datatypes",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.interface.array.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/interfaces",
				"patcherrelativepath" : "../../abstractions/interfaces",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.map1.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "oovu.util.fixedarray.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/abstractions/utilities",
				"patcherrelativepath" : "../../abstractions/utilities",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "omod.output~.view.maxpat",
				"bootpath" : "/Users/josiah/Documents/Freelance/barroso/oovu/modules/omod.output~",
				"patcherrelativepath" : "../omod.output~",
				"type" : "JSON",
				"implicit" : 1
			}
, 			{
				"name" : "ambipanning~.mxo",
				"type" : "iLaX"
			}
 ]
	}

}
